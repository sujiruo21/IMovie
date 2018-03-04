package com.zl.imovie.network.http;

import com.zl.imoivesdk.okhttp.CommonOkhttpClient;
import com.zl.imoivesdk.okhttp.listener.DisposeDataHandle;
import com.zl.imoivesdk.okhttp.listener.DisposeDataListener;
import com.zl.imoivesdk.okhttp.request.CommonRequest;
import com.zl.imoivesdk.okhttp.request.RequestParams;
import com.zl.imoivesdk.okhttp.response.CommonJsonCallback;
import com.zl.imovie.module.recommand.BaseRecommandModel;

/**
 * @author zl
 * @function 存放应用中所有的请求
 */

public class RequestCenter {

    /**
     * 根据参数发送所有的post请求
     *
     * @param url
     * @param params
     * @param listener
     * @param clazz
     */
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkhttpClient.sendRequest(CommonRequest.createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    public static void requestRecommendData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener, BaseRecommandModel.class);
    }

}
