package com.v.module_flutter.flutter_boost;

import static com.v.module_flutter.flutter_boost.MyFlutterFragment.methodChannel;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;
import com.v.module_flutter.R;
import com.v.module_flutter.helper.MethodChannelListener;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodChannel;

public class MyFlutterActivity extends AppCompatActivity {

    private MyFlutterFragment fragment;
    public FlutterBoostRouteOptions pageParams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter);
        String jsonParams = getIntent().getStringExtra("params");
        Log.i("zm1234", "onCreate: jsonParams:" + jsonParams);
        if (!TextUtils.isEmpty(jsonParams)) {
            pageParams = new Gson().fromJson(jsonParams, FlutterBoostRouteOptions.class);
        }
        if (pageParams != null) {
            fragment = new MyFlutterFragment
                    .CachedEngineFragmentBuilder(MyFlutterFragment.class)
                    .destroyEngineWithFragment(false)
                    .uniqueId(pageParams.uniqueId())
                    .url(pageParams.pageName())
                    .urlParams(pageParams.arguments())
                    .build();
            getSupportFragmentManager().beginTransaction().add(R.id.cl_root_layout, fragment).commit();
        }

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Android调用flutter方法
                Map<String, String> params = new HashMap<String,String>();
                params.put("msg","这是来自 Android 端的参数");
                // 调用 Flutter 中的方法
                methodChannel.invokeMethod("android.invoke/flutter", params, new MethodChannel.Result() {
                    @Override
                    public void success(@Nullable Object result) {
                        Log.i("zm1234", "MyFlutterActivity success()"+result);
                    }

                    @Override
                    public void error(@NonNull String errorCode, @Nullable String errorMessage, @Nullable Object errorDetails) {
                        Log.i("zm1234", "MyFlutterActivity error() errorCode:"+errorCode+" errorMessage:"+errorMessage+" errorDetails:"+errorDetails);
                    }

                    @Override
                    public void notImplemented() {
                        Log.i("zm1234", "MyFlutterActivity notImplemented()");
                    }
                });
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (fragment != null) {
            fragment.onResume();
            Log.i("zm1234", "MyFlutterActivity onResume");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (fragment != null) {
            fragment.onPause();
            Log.i("zm1234", "MyFlutterActivity onPause");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (fragment != null) {
            fragment.onPause();
            Log.i("zm1234", "MyFlutterActivity onStop");
        }
    }

    /**
     * 物理返回键监听
     */
    @Override
    public void onBackPressed() {
        Log.i("zm1234", "MyFlutterActivity onBackPressed()");
        methodChannel.invokeMethod("onBackPressed", new HashMap<String, String>(), new MethodChannelListener() {
            @Override
            public void success(@Nullable Object result) {
                Log.i("zm1234", "MyFlutterActivity onBackPressed() success() result:"+result);
                if (result instanceof Boolean) {
                    Boolean value = (Boolean) result;
                    if (!value) {
                        MyFlutterActivity.super.onBackPressed();
                    }
                }
            }
        });
    }
}
