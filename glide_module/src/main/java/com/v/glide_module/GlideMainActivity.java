package com.v.glide_module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * glide使用
 */
public class GlideMainActivity extends AppCompatActivity {
    private ImageView img;
    private String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586669970027&di=06f69c32cf1636e99944776d0f049dad&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F21%2F09%2F01200000026352136359091694357.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_main);

        img = findViewById(R.id.img);

        Glide.with(this)
        .load(url)
        .into(img);
    }
}