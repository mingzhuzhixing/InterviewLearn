package com.v.module_animator.scale;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.v.module_animator.R;

/**
 * ScaleX与ScaleY
 */
public class ScaleActivity extends AppCompatActivity {
    private TextView tv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
        tv_view = findViewById(R.id.tv_view);
    }

    public void scaleXClick(View view) {
        tv_view.setPivotX(0);
        tv_view.setPivotY(0);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(tv_view, View.SCALE_X, 1.0f, 2.0f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(tv_view, View.SCALE_Y, 1.0f, 2.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
}