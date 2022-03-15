package com.v.textview_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.v.textview_module.marquee.MarqueeActivity;

/**
 * 文本 主activity
 */
public class TextviewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_main);
    }

    //跑马灯效果
    public void marqueeClick(View view) {
        startActivity(new Intent(this, MarqueeActivity.class));
    }
}