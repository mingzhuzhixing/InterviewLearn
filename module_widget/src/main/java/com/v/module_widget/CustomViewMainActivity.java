package com.v.module_widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_widget.color_track.ColorTrackTextViewActivity;
import com.v.module_widget.color_track.ViewPageActivity;
import com.v.module_widget.progess_bar.ProgressBarActivity;
import com.v.module_widget.rating_bar.RatingBarActivity;
import com.v.module_widget.shape_view.ShapeViewActivity;
import com.v.module_widget.step_counter.StepCounterActivity;
import com.v.module_widget.text_view.CustomTextViewActivity;
import com.v.module_widget.watch_view.CustomWatchActivity;

public class CustomViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    /**
     * 自定义钟表view
     */
    public void customWatchView(View view) {
        startActivity(new Intent(this, CustomWatchActivity.class));
    }

    /**
     * 自定义textview
     */
    public void customTextView(View view) {
        startActivity(new Intent(this, CustomTextViewActivity.class));
    }

    /**
     * 自定义步数计数器
     */
    public void customStepCounter(View view) {
        startActivity(new Intent(this, StepCounterActivity.class));
    }

    /**
     * 滑动字体颜色跟踪变色
     */
    public void colorTrackView(View view) {
        startActivity(new Intent(this, ColorTrackTextViewActivity.class));
    }

    /**
     * 滑动viewpager字体颜色跟踪变色
     */
    public void colorTrackViewpager(View view) {
        startActivity(new Intent(this, ViewPageActivity.class));
    }

    /**
     * 自定义圆形进度条
     */
    public void progressBarClick(View view) {
        startActivity(new Intent(this, ProgressBarActivity.class));
    }

    /**
     * 变换不同的图形
     */
    public void shapeViewClick(View view) {
        startActivity(new Intent(this, ShapeViewActivity.class));
    }

    /**
     * 评分控件 RatingBar
     */
    public void ratingBarClick(View view) {
        startActivity(new Intent(this, RatingBarActivity.class));
    }
}
