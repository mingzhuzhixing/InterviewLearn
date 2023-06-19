package com.v.module_flutter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.idlefish.flutterboost.FlutterBoost;
import com.idlefish.flutterboost.FlutterBoostRouteOptions;
import com.v.module_flutter.flutter_native.FlutterChildActivity;
import com.v.module_flutter.flutter_native.FlutterFragmentActivity;

import java.util.HashMap;
import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;

public class FlutterMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter_main);
    }

    /**
     * 直接打开Flutter页面
     */
    public void openFlutterClick(View view) {
        //startActivity(FlutterActivity.createDefaultIntent(this));
        //startActivity(FlutterActivity.withNewEngine().initialRoute("route_page").build(this));

        startActivity(FlutterActivity.withCachedEngine("my_engine_id").build(this));
    }

    public void flutterChildActivityClick(View view) {
        startActivity(new Intent(this, FlutterChildActivity.class));
    }

    /**
     * Activity 中添加 FlutterFragment
     */
    public void flutterFragmentClick(View view) {
        startActivity(new Intent(this, FlutterFragmentActivity.class));
    }

    /**
     * flutter boost 打开flutter
     */
    public void flutterBoostClick(View view) {
        Map<String,Object> pArgument = new HashMap<>();
        pArgument.put("key","value");
        pArgument.put("name","这是Android带过来的数据");
        FlutterBoostRouteOptions options = new FlutterBoostRouteOptions.Builder()
                .pageName("index")
                .arguments(pArgument)
                .requestCode(1111)
                .build();
        FlutterBoost.instance().open(options);
    }
}