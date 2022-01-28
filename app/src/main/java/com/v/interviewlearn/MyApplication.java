package com.v.interviewlearn;

import com.v.base_module.BaseApplication;
import com.v.dialog_module.toast.ToastUtils;
import com.youshu.network_module.HttpHelper;
import com.youshu.network_module.xutils.XutilsProcessor;


public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化切换网络框架
//        HttpHelper.init(new VolleyProcessor(this));
//        HttpHelper.init(new OkHttpProcessor());
        HttpHelper.init(new XutilsProcessor(this));

        ToastUtils.init(this);
    }
}
