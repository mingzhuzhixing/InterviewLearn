package com.v.module_widget.color_track;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;

import com.v.module_widget.R;

/**
 * 滑动字体颜色跟踪变色
 */
public class ColorTrackTextViewActivity extends AppCompatActivity {

    private ColorTrackTextView mTvColorTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_track_text_view);

        mTvColorTrack = findViewById(R.id.tv_color_track);
    }

    public void leftToRight(View view) {
        mTvColorTrack.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mTvColorTrack.setProgress(animatedValue);
            }
        });
        valueAnimator.start();
    }

    public void rightToLeft(View view) {
        mTvColorTrack.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                mTvColorTrack.setProgress(animatedValue);
            }
        });
        valueAnimator.start();
    }
}