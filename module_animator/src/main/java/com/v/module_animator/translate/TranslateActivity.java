package com.v.module_animator.translate;

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
public class TranslateActivity extends AppCompatActivity {
    private TextView tv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        tv_view = findViewById(R.id.tv_view);
    }

    public void translateXClick(View view) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(tv_view, View.TRANSLATION_X, 1.0f, 2.0f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(tv_view, View.TRANSLATION_Y, 1.0f, 2.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
}