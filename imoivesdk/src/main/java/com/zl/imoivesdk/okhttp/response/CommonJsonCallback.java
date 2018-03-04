package com.zl.imoivesdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.zl.imoivesdk.okhttp.exception.OkHttpException;
import com.zl.imoivesdk.okhttp.listener.DisposeDataHandle;
import com.zl.imoivesdk.okhttp.listener.DisposeDataListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author zl
 * @function 处理json的回调
 */

public class CommonJsonCallback implements Callback {

    /**
     * 与服务器返回字段的一个对应关系
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error

    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 请求失败处理
     *
     * @param call
     * @param e
     */
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    /**
     * 处理服务器返回的响应数据
     *
     * @param objResponse
     */
    private void handleResponse(Object objResponse) {
        //1.判断返回值是否为空
        if (objResponse == null || objResponse.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        //2.将result直接返回或者转化成Class对象
        try {
            JSONObject result = new JSONObject(objResponse.toString());
            //判断返回值中是否有响应码
            if(result.has(RESULT_CODE)){
                //取出响应码判断是否为0
                if(result.getInt(RESULT_CODE) == RESULT_CODE_VALUE){
                    //判断是否需要解析成Class对象
                    if(mClass == null){
                        mListener.onSuccess(objResponse);
                    }else{
                        Object obj = new Gson().fromJson(result.toString(),mClass);
                        if(obj != null){
                            mListener.onSuccess(obj);
                        }else {
                            mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                        }
                    }
                }else{
                    mListener.onFailure(new OkHttpException(OTHER_ERROR,result.get(RESULT_CODE)));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
        }
    }
}
