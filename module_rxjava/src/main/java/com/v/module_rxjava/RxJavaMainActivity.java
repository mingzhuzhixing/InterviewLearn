package com.v.module_rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RxJavaMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_main);
    }

    /**
     * 进入rxjava 和 retrofit混合使用页
     */
    public void entryRetrofit(View view) {
        startActivity(new Intent(this, UserInfoActivity.class));
    }

    /**
     * 防抖对比测试
     */
    public void rxJavaDebounce(View view) {
        startActivity(new Intent(this, DebounceCompareActivity.class));
    }
}