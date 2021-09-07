package com.v.interviewlearn;

import android.app.Application;

import com.youshu.network_module.HttpHelper;
import com.youshu.network_module.xutils.XutilsProcessor;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化切换网络框架
//        HttpHelper.init(new VolleyProcessor(this));
//        HttpHelper.init(new OkHttpProcessor());
        HttpHelper.init(new XutilsProcessor(this));
    }
}
