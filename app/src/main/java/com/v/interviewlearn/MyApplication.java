package com.v.interviewlearn;
import com.v.module_base.BaseApplication;
import com.v.module_dialog.toast.ToastUtils;
import com.v.module_thread.thread.ThreadPool;

import comv.module_network.HttpHelper;
import comv.module_network.xutils.XutilsProcessor;


public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化切换网络框架
//        HttpHelper.init(new VolleyProcessor(this));
//        HttpHelper.init(new OkHttpProcessor());
        HttpHelper.init(new XutilsProcessor(this));

        ToastUtils.init(this);

        //初始化线程
        ThreadPool.startup();
    }
}
