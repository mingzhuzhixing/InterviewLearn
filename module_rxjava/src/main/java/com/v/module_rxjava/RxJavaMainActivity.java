package com.v.module_rxjava;

import android.content.Intent;
import android.view.View;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_rxjava.debounce.DebounceCompareActivity;
import com.v.module_rxjava.debounce.UserInfoActivity;
import com.v.module_rxjava.lifecycle.RxJavaLifecycleActivity;
import com.v.module_rxjava.operator.OperatorActivity;

public class RxJavaMainActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rxjava_main;
    }

    @Override
    protected String setTitle() {
        return "RxJava";
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

    /**
     * 操作符
     */
    public void operatorClick(View view) {
        startActivity(new Intent(this, OperatorActivity.class));
    }

    /**
     * RxJava的生命周期
     */
    public void rxJavaLifecycleClick(View view) {
        startActivity(new Intent(this, RxJavaLifecycleActivity.class));
    }
}