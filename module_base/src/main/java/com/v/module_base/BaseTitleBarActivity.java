package com.v.module_base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity基类  带有标题
 */
@SuppressLint("NonConstantResourceId")
public abstract class BaseTitleBarActivity extends BaseActivity {

    private Unbinder unbinder;
    protected FrameLayout mFlContainer;

    @Override
    public int getBaseLayoutId() {
        return R.layout.activity_base_title_bar;
    }

    /**
     * 初始化数据
     */
    public void initData() {
        //全局悬浮框
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        View floatView = LayoutInflater.from(this).inflate(R.layout.window_float_layout, null);
//        addContentView(floatView, params);

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
    }

    @Override
    public void initPresenter() {
        // 空实现
    }

    @Override
    public void initActionBar() {
        // 空实现
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
     * 处理逻辑
     */
    public void processLogical() {

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
