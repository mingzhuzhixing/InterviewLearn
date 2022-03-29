package com.v.interviewlearn;
import android.content.Context;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.v.module_base.BaseApplication;
import com.v.module_dialog.toast.ToastUtils;
import com.v.module_recyclerview.swipe_refresh_layout.refreshview.VerticalMouldingFooter;
import com.v.module_recyclerview.swipe_refresh_layout.refreshview.VerticalMouldingHeader;
import com.v.module_thread.thread.ThreadPool;

import comv.module_network.HttpHelper;
import comv.module_network.xutils.XutilsProcessor;


public class MyApplication extends BaseApplication {

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                VerticalMouldingHeader header = new VerticalMouldingHeader(context);
                return header;
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                VerticalMouldingFooter footer = new VerticalMouldingFooter(context);
                return footer;
            }
        });
    }

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
