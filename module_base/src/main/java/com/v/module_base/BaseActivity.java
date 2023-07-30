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
        setContentView(getBaseLayoutId());
        initData();
        initPresenter();
        //隐藏标题栏
        initActionBar();
        //处理逻辑
        processLogical();
    }

    /**
     * 页面layoutId
     */
    public abstract int getBaseLayoutId();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化Presenter
     */
    public abstract void initPresenter();

    /**
     * 初始化顶部ActionBar
     */
    public abstract void initActionBar();

    /**
     * 处理逻辑
     */
    public abstract void processLogical();
}
