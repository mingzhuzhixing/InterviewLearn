package com.v.module_base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity基类  带有标题
 */
@SuppressLint("NonConstantResourceId")
public abstract class BaseTitleBarActivity extends AppCompatActivity {

    private Unbinder unbinder;
    protected FrameLayout mFlContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_title_bar);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View floatView = LayoutInflater.from(this).inflate(R.layout.window_float_layout, null);
        addContentView(floatView, params);

        //设置布局
        mFlContainer = findViewById(R.id.fl_container);
        if (getLayoutId() > 0) {
            mFlContainer.addView(getLayoutInflater().inflate(getLayoutId(), null));
            if (isNeedButterKnife()) {
                unbinder = ButterKnife.bind(this);
            }
        }

        //设置标题
        TextView mTvTile = findViewById(R.id.tv_title);
        mTvTile.setText(TextUtils.isEmpty(setTitle()) ? "" : setTitle());

        initData();
        processLogical();
    }

    /**
     * 返回
     */
    public void onBackClick(View view) {
        onBackPressed();
    }

    /**
     * 页面layoutId
     */
    protected abstract int getLayoutId();

    protected abstract String setTitle();

    /**
     * 初始化数据
     */
    protected void initData(){

    }

    /**
     * 处理逻辑
     */
    protected void processLogical(){

    }

    /**
     * 是否需要ButterKnife
     */
    protected boolean isNeedButterKnife() {
        return false;
    }

    /**
     * startActivity
     */
    public void startActivity(Class zclass) {
        startActivity(new Intent(this, zclass));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
