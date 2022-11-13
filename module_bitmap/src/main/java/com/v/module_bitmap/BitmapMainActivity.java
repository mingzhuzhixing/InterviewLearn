package com.v.module_bitmap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.v.module_bitmap.load.LoadBitmapActivity;
import com.v.module_bitmap.recycle.RecycleBitmapActivity;
import com.v.module_bitmap.utils.BitmapHelper;
import com.v.module_bitmap.utils.BitmapUtils;
import com.v.module_glide.GlideUtils;

import java.io.ByteArrayInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * glide使用
 */
@SuppressLint("NonConstantResourceId")
public class BitmapMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_main);
    }

    public void loadBimap(View view) {
        startActivity(new Intent(this, LoadBitmapActivity.class));
    }

    public void recycleBitmap() {
        startActivity(new Intent(this, RecycleBitmapActivity.class));
    }
}