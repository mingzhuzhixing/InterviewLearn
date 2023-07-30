package com.v.module_widget.motion_layout;

import android.view.View;
import android.widget.FrameLayout;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

/**
 * 新控件MotionLayout
 */
public class MotionLayoutActivity extends BaseTitleBarActivity {
    private FrameLayout flRootView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_motion_layout;
    }

    @Override
    protected String setTitle() {
        return "新控件MotionLayout使用";
    }

    @Override
    public void initData() {
        flRootView = findViewById(R.id.fl_root_view);
    }

    @Override
    public void onBackClick(View view) {
        if (flRootView.getChildCount() <= 0) {
            super.onBackClick(view);
        } else {
            removeFragment();
        }
    }

    @Override
    public void processLogical() {

    }

    public void layoutView1Click(View view) {
        flRootView.addView(new MotionLayoutView1(this));
    }

    public void layoutView2Click(View view) {
        flRootView.addView(new MotionLayoutView2(this));
    }

    public void removeFragment() {
        flRootView.removeAllViews();
    }

    @Override
    public void onBackPressed() {
        if (flRootView.getChildCount() <= 0) {
            super.onBackPressed();
        } else {
            removeFragment();
        }
    }
}