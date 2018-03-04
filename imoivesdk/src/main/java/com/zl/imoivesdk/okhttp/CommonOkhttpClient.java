package com.zl.imoivesdk.okhttp;

import android.util.TimeUtils;

import com.zl.imoivesdk.okhttp.https.HttpsUtils;
import com.zl.imoivesdk.okhttp.listener.DisposeDataHandle;
import com.zl.imoivesdk.okhttp.request.RequestParams;
import com.zl.imoivesdk.okhttp.response.CommonJsonCallback;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zl
 * @function 1.请求的发送
 * 2.请求参数的配置
 * 3.https的支持
 */

public class CommonOkhttpClient {

    //请求超时
    public static final int TIME_OUT = 30;
    public static OkHttpClient mOkhttpClient;

    //为client配置参数，一般情况下超时时间一致
    static {
        //创建Client对象的构建者
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        //设置连接、读、写的超时时间
        okhttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        //允许重定向
        okhttpBuilder.followRedirects(true);

        //https支持
        okhttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        okhttpBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());

        //生成Client对象
        mOkhttpClient = okhttpBuilder.build();
    }

    /**
     * 发送具体的http/https请求
     * @param request
     * @param handle
     * @return Call对象
     */
    public static Call sendRequest(Request request, DisposeDataHandle handle){
        Call call = mOkhttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }
}
