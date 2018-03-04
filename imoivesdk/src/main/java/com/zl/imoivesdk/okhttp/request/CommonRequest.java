package com.zl.imoivesdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * @author zl
 * @function 接收请求参数，为我们生成request对象
 */

public class CommonRequest {

    /**
     *
     * @param url
     * @param params
     * @return 返回Post类型的request对象
     */
    public static Request createPostRequest(String url,RequestParams params){
        FormBody.Builder mFormBuilder = new FormBody.Builder();
        if(params != null){
            //遍历Map将请求参数添加到FormBody.Builder类中
            for(Map.Entry<String,String> entry:params.urlParams.entrySet()){
                mFormBuilder.add(entry.getKey(),entry.getValue());
            }
        }

        //构建FormBody对象
        FormBody mFormBody = mFormBuilder.build();
        return new Request.Builder().url(url).post(mFormBody).build();
    }

    /**
     *
     * @param url
     * @param params
     * @return 返回Get类型的request对象
     */
    public static Request createGetRequest(String url,RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(params != null){
            //遍历Map将请求参数添加到FormBody.Builder类中
            for(Map.Entry<String,String> entry:params.urlParams.entrySet()){
                urlBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length()-1)).get().build();
    }
}
