package com.v.module_flutter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}