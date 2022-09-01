package com.v.interviewlearn;

import android.content.Context;

import com.google.gson.Gson;
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
//import io.flutter.embedding.engine.FlutterEngine;
//import io.flutter.embedding.engine.FlutterEngineCache;
//import io.flutter.embedding.engine.dart.DartExecutor;
//import io.flutter.view.FlutterMain;


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
        initFlutterEngine();
    }

    /**
     * 初始化flutter引擎
     *
     * 1、加载 FlutterActivity 页面时明显看到一段时间的黑屏，这段时间主要是启动 Flutter 引擎（FlutterEngine），
     * Flutter 引擎启动的时间在不同手机上不同，性能越好的手机越短。同时每一个 FlutterActivity 页面都会启动一个引擎，
     * 所以强烈建议不要在一个项目中创建多个 FlutterActivity（或者启动多个 FlutterActivity 实例），否则内存会越来越大；
     *
     * 2、为了减少 FlutterActivity 页面的延迟时间和多个 FlutterActivity 实例内存一直增长问题，我们可以使用 Flutter 引擎（FlutterEngine）缓存，
     * 在启动 FlutterActivity 前先启动 Flutter 引擎，然后使用缓存的引擎加载页面，通常将其放在 Application 中；
     *
     * 3、这里要注意，使用缓存引擎时，其生命周期不在是 FlutterActivity（或者 FlutterFragment）的生命周期，而是整个 App 的生命周期（在Application 中的创建和销毁）。当然也可以提前销毁：
     * flutterEngine.destroy()
     */
    private void initFlutterEngine() {
//        FlutterEngine flutterEngine = new FlutterEngine(this);
//        flutterEngine.getDartExecutor().executeDartEntrypoint(
//                DartExecutor.DartEntrypoint.createDefault()
//        );
//        // Cache the FlutterEngine to be used by FlutterActivity.
//        FlutterEngineCache.getInstance().put("my_engine_id", flutterEngine);

        //fluterboost
//        FlutterBoost.instance().setup(this, new FlutterBoostDelegate() {
//            @Override
//            public void pushNativeRoute(FlutterBoostRouteOptions options) {
//            }
//            @Override
//            public void pushFlutterRoute(FlutterBoostRouteOptions options) {
//                try {
//                    ARouter.getInstance()
//                            .build(FlutterConstants.PATH_PRE+FlutterConstants.PATH_FLUTTER_ACTIVITY)
//                            .withString("params", new Gson().toJson(options))
//                            .navigation();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }, engine -> {
//        });
    }
}
