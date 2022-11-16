package com.v.module_bitmap;

import android.content.Intent;
import android.view.View;
import com.v.module_base.BaseTitleBarActivity;
import com.v.module_bitmap.load.LoadBitmapActivity;
import com.v.module_bitmap.recycle.RecycleBitmapActivity;

/**
 * bitmap使用
 */
public class BitmapMainActivity extends BaseTitleBarActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bitmap_main;
    }

    @Override
    protected String setTitle() {
        return "Bitmap";
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void processLogical() {

    }

    public void loadBimap(View view) {
        startActivity(new Intent(this, LoadBitmapActivity.class));
    }

    public void recycleBitmap(View view) {
        startActivity(new Intent(this, RecycleBitmapActivity.class));
    }
}