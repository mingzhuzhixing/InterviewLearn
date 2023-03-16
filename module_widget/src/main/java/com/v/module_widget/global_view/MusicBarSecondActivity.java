package com.v.module_widget.global_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.v.module_base.BaseActivity;
import com.v.module_base.BaseTitleBarActivity;
import com.v.module_widget.R;

public class MusicBarSecondActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_music_bar_second;
    }

    @Override
    protected String setTitle() {
        return "MusicBarSecondActivity";
    }
}