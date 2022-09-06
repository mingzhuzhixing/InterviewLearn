package com.v.module_animator.rotate;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_animator.R;

/**
 * ScaleX与ScaleY
 */
public class RotateActivity extends AppCompatActivity {
    private TextView tv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate);
        tv_view = findViewById(R.id.tv_view);
    }

    public void rotateXClick(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(tv_view, View.ROTATION_X, 0, 180);
        animatorX.setDuration(2000);
        animatorX.start();
    }

    public void rotateYClick(View view) {
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(tv_view, View.ROTATION_Y, 0, 180);
        animatorY.setDuration(2000);
        animatorY.start();
    }

    public void rotateNClick(View view) {
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(tv_view, View.ROTATION_Y, 0, 180, 180, 0);
        animatorY.setDuration(2000);
        animatorY.start();
    }
}