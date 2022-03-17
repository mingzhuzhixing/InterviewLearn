package com.v.module_glide;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

/**
 * glide使用
 */
public class GlideMainActivity extends AppCompatActivity {
    private ImageView img;
    private String url = "https://img0.baidu.com/it/u=3348828363,1819926127&fm=26&fmt=auto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_main);

        img = findViewById(R.id.img);

        Glide.with(this).load(url).into(img);
    }
}