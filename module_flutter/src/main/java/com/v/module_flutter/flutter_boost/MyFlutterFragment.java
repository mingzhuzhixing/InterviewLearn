package com.v.module_flutter.flutter_boost;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.idlefish.flutterboost.containers.FlutterBoostFragment;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;

public class MyFlutterFragment extends FlutterBoostFragment {
    public static String CHANNEL_NATIVE = "com.v.interviewlearn.flutter_app/native";
    public static MethodChannel methodChannel;
    public static EventChannel.EventSink eventSink;
    private Context context;
    private String scheme;


    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        EventChannel e = new EventChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "com.v.interviewlearn.flutter_app/nativeEvent");
        e.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, EventChannel.EventSink events) {
                eventSink = events;
            }

            @Override
            public void onCancel(Object arguments) {

            }
        });

        //flutter 调用Android
        methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_NATIVE);
        methodChannel.setMethodCallHandler((call, result) -> {
            switch (call.method) {
                case "flutter.invoke/android":
                    // 获取flutter传入的参数
                    String argument = call.argument("");
                    Log.i("zm1234", "正在执行原生方法，传入的参数是：" + argument);
                    result.success("flutter 调用android 成功");
                    break;
                default:
                    result.notImplemented();
                    break;
            }
        });
    }
}
