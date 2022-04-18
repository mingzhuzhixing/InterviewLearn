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

        ImageView img_2 = findViewById(R.id.img_2);
        //loadImage(Context context, String url, ImageView iv, int ScaleType, int error_resid)
        String path = "http://feed.youshu.cc/readwith/media/picture/625d354029313.png";
        GlideUtils.loadImage(this, path, img_2, GlideUtils.SCALETYPE_NOTHING, -1);

        ImageView img_3 = findViewById(R.id.img_3);
        Glide.with(this).load(path).into(img_3);
    }
}