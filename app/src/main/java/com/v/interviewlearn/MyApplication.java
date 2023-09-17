package com.v.interviewlearn;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostDelegate;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.v.module_base.BaseApplication;
import com.v.module_dialog.toast.ToastUtils;
import com.v.module_flutter.flutter_boost.MyFlutterActivity;
import com.v.module_recyclerview.swipe_refresh_layout.refreshview.VerticalMouldingFooter;
import com.v.module_recyclerview.swipe_refresh_layout.refreshview.VerticalMouldingHeader;
import com.v.module_thread.thread.ThreadPool;

import comv.module_network.HttpHelper;
import comv.module_network.xutils.XutilsProcessor;
import io.realm.Realm;
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

        //realm数据库使用之前的初始化
        Realm.init(this);

        //浮窗初始化
//        AppHelper helper = AppHelper.builder()
//                .setLayout(com.v.module_widget.R.layout.window_float_layout)
//                .enableFx()
//                .build();
//        FloatingX.init(helper);
    }

    /**
     * 初始化flutter引擎
     * <p>
     * 1、加载 FlutterActivity 页面时明显看到一段时间的黑屏，这段时间主要是启动 Flutter 引擎（FlutterEngine），
     * Flutter 引擎启动的时间在不同手机上不同，性能越好的手机越短。同时每一个 FlutterActivity 页面都会启动一个引擎，
     * 所以强烈建议不要在一个项目中创建多个 FlutterActivity（或者启动多个 FlutterActivity 实例），否则内存会越来越大；
     * <p>
     * 2、为了减少 FlutterActivity 页面的延迟时间和多个 FlutterActivity 实例内存一直增长问题，我们可以使用 Flutter 引擎（FlutterEngine）缓存，
     * 在启动 FlutterActivity 前先启动 Flutter 引擎，然后使用缓存的引擎加载页面，通常将其放在 Application 中；
     * <p>
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

        Log.i("zm1234", "MyApplication initFlutterEngine()");
        //fluterboost
        FlutterBoost.instance().setup(this, new FlutterBoostDelegate() {
            @Override
            public void pushNativeRoute(FlutterBoostRouteOptions options) {
                Log.i("zm1234", "MyApplication pushNativeRoute:" + JSON.toJSONString(options));
                //这里根据options.pageName来判断你想跳转哪个页面，这里简单给一个
//                Intent intent = new Intent(FlutterBoost.instance().currentActivity(), YourTargetAcitvity.class);
//                FlutterBoost.instance().currentActivity().startActivityForResult(intent, options.requestCode());
            }

            @Override
            public void pushFlutterRoute(FlutterBoostRouteOptions options) {
                Log.i("zm1234", "MyApplication pushFlutterRoute:" + JSON.toJSONString(options));
//                Intent intent = new FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity.class)
//                        .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.opaque)
//                        .destroyEngineWithActivity(false)
//                        .uniqueId(options.uniqueId())
//                        .url(options.pageName())
//                        .urlParams(options.arguments())
//                        .build(FlutterBoost.instance().currentActivity());
               try {
                   Intent intent = new Intent(FlutterBoost.instance().currentActivity(), MyFlutterActivity.class);
                   intent.putExtra("params", new Gson().toJson(options));
                   FlutterBoost.instance().currentActivity().startActivity(intent);
               } catch (Exception e) {
                   e.printStackTrace();
               }
            }

            @Override
            public boolean popRoute(FlutterBoostRouteOptions options) {
                Log.i("zm1234", "MyApplication popRoute:" + JSON.toJSONString(options));
                return FlutterBoostDelegate.super.popRoute(options);
            }
        }, engine -> {
            Log.i("zm1234", "MyApplication engine:" + engine);
        });
    }
}
