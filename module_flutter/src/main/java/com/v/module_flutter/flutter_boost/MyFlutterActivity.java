package com.v.module_flutter.flutter_boost;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;
import com.v.module_flutter.R;

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
}
