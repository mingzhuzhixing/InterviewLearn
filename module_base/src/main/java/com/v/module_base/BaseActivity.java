package com.v.module_base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
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
        if (initActionbarStatus()) {
            hideActionBar();
        }
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
     * 处理逻辑
     */
    public abstract void processLogical();

    /**
     * 初始化顶部ActionBar状态
     */
    public abstract boolean initActionbarStatus();

    /**
     * 隐藏actionbar
     */
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
     * 显示actionbar
     */
    public void showActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }
}
