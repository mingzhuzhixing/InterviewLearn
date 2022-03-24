package com.v.module_base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initData();
        //处理逻辑
        processLogical();
    }

    /**
     * 页面layoutId
     */
    abstract int getLayoutId();

    /**
     * 初始化数据
     */
    abstract void initData();

    /**
     * 处理逻辑
     */
    abstract void processLogical();
}
