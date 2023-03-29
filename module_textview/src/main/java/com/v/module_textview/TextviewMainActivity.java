package com.v.module_textview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_textview.at.RichTextActivity;
import com.v.module_textview.ellipsize_view.EllipsizeLayoutActivity;
import com.v.module_textview.marquee.MarqueeActivity;
import com.v.module_textview.tagview.HtmlTextViewActivity;
import com.v.module_textview.tagview.SpannableStringActivity;
import com.v.module_textview.tagview.TagTextViewActivity;
import com.v.module_textview.url_parse.UrlQueryParameterActivity;

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

    /**
     * 获取url中的参数值
     */
    public void URLQueryParamsClick(View view) {
        startActivity(new Intent(this, UrlQueryParameterActivity.class));
    }

    public void atUserClick(View view) {
        startActivity(new Intent(this, RichTextActivity.class));
    }

    public void singleLineClick(View view) {
        startActivity(new Intent(this, SingleLineActivity.class));
    }

    /**
     *  Android开发中，在LinearLayout中如何让左边TextView显示省略号，而右边的空间正常显示，完美解决方案：EllipsizeLayout
     */
    public void ellipsizeClick(View view) {
        startActivity(new Intent(this, EllipsizeLayoutActivity.class));
    }
}