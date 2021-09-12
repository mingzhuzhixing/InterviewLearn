package com.youshu.rxjava_module;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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