package com.v.module_flutter.flutter_boost;

import android.content.Context;

import androidx.annotation.NonNull;

import com.idlefish.flutterboost.containers.FlutterBoostFragment;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;

public class MyFlutterFragment extends FlutterBoostFragment {
    public static String CHANNEL_NATIVE = "com.v.module_flutter.flutter_app/native";
    public static MethodChannel methodChannel;
    public static EventChannel.EventSink eventSink;
    private Context context;
    private String scheme;


    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        EventChannel e = new EventChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "com.v.module_flutter.flutter_app/nativeEvent");
        e.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, EventChannel.EventSink events) {
                eventSink = events;
            }

            @Override
            public void onCancel(Object arguments) {

            }
        });
        methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL_NATIVE);
        methodChannel.setMethodCallHandler((call, result) -> {

        });
    }
}
