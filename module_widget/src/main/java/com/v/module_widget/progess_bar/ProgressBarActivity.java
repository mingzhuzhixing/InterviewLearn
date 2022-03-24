package com.v.module_widget.progess_bar;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

public class ProgressBarActivity extends BaseTitleBarActivity {
    private ProgressBarView mProgressView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_progress_bar;
    }

    @Override
    protected void initData() {
        mProgressView = findViewById(R.id.progress_view);
    }

    @Override
    protected String setTitle() {
        return "自定义圆形进度条";
    }

    @Override
    protected void processLogical() {

    }

    public void startClick(View view) {
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 100);
        mProgressView.setMaxProgress(100);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mProgressView.setCurrentProgress((int) value);
            }
        });
        valueAnimator.start();
    }
}