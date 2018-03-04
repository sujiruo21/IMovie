package com.zl.imovie.application;

import android.app.Application;

/**
 * Created by zl on 18-3-3.
 * 1.整个程序的入口
 * 2.常用第三方SDK的初始化
 * 3.为整个应用的其他模块提供上下文
 * 4.通常以单例的方式构建对象
 */

public class IMovieApplication extends Application {

    private static IMovieApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static IMovieApplication getInstance(){
        return mApplication;
    }
}
