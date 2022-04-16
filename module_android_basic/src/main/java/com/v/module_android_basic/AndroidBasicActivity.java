package com.v.module_android_basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.v.module_android_basic.config_changes.ConfigChangesActivity;
import com.v.module_android_basic.hook.HookActivity;


public class AndroidBasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_basic);
    }

    /**
     * hook点击事件
     */
    public void hookClick(View view) {
        startActivity(new Intent(this, HookActivity.class));
    }

    /**
     * fragment 使用接口
     */
    public void fragmentInterfaceClick(View view) {
        startActivity(new Intent(this, FragmentActivity.class));
    }

    /**
     * config 配置
     */
    public void configChangesClick(View view) {
        startActivity(new Intent(this, ConfigChangesActivity.class));
    }
}