package com.v.module_animator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.v.module_animator.alpha.AlphaActivity;
import com.v.module_animator.rotate.RotateActivity;
import com.v.module_animator.scale.ScaleActivity;
import com.v.module_animator.translate.TranslateActivity;

public class AnimatorMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_main);
    }

    /**
     * 平移
     */
    public void translationClick(View view) {
        startActivity(new Intent(this, TranslateActivity.class));
    }

    /**
     * 旋转
     */
    public void rotationClick(View view) {
        startActivity(new Intent(this, RotateActivity.class));
    }

    /**
     * 缩放
     */
    public void scaleClick(View view) {
        startActivity(new Intent(this, ScaleActivity.class));
    }

    /**
     * 透明度
     */
    public void alphaClick(View view) {
        startActivity(new Intent(this, AlphaActivity.class));
    }
}