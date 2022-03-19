package com.v.module_widget.step_counter;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.v.module_widget.R;

/**
 * 步数计数器
 */
public class StepCounterActivity extends AppCompatActivity {
    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        final QQStepView stepView = findViewById(R.id.stepView);
        stepView.setStepMax(4000);

        //属性动画 后面讲内容
        mValueAnimator = ObjectAnimator.ofFloat(0, 3000);
        mValueAnimator.setDuration(2000);
        mValueAnimator.setInterpolator(new DecelerateInterpolator());//先快后慢
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                stepView.setCurrentStep((int) value);
            }
        });
    }

    /**
     * 开始动画
     */
    public void startAnimator(View view) {
        if (mValueAnimator != null && !mValueAnimator.isRunning()) {
            mValueAnimator.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mValueAnimator != null) {
            mValueAnimator.cancel();
        }
    }
}