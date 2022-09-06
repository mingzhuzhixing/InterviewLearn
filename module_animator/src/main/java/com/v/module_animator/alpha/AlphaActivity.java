package com.v.module_animator.alpha;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_animator.R;

/**
 * ScaleX与ScaleY
 */
public class AlphaActivity extends AppCompatActivity {
    private TextView tv_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        tv_view = findViewById(R.id.tv_view);
    }

    public void alphaClick(View view) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(tv_view, View.ALPHA, 1.0f, 0.1f);
        alpha.setDuration(2000);
        alpha.start();
    }
}