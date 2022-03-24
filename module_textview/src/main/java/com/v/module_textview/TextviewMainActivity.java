package com.v.module_textview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_textview.marquee.MarqueeActivity;
import com.v.module_textview.tagview.HtmlTextViewActivity;
import com.v.module_textview.tagview.SpannableStringActivity;
import com.v.module_textview.tagview.TagTextViewActivity;

/**
 * 文本 主activity
 */
public class TextviewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview_main);
    }

    public void tagTextView(View view) {
        startActivity(new Intent(this, TagTextViewActivity.class));
    }

    public void htmlTextView(View view) {
        startActivity(new Intent(this, HtmlTextViewActivity.class));
    }

    public void spannableStringTextView(View view) {
        startActivity(new Intent(this, SpannableStringActivity.class));
    }

    //跑马灯效果
    public void marqueeClick(View view) {
        startActivity(new Intent(this, MarqueeActivity.class));
    }
}