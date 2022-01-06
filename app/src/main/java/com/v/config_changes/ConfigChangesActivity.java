package com.v.config_changes;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.v.interviewlearn.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 横竖屏切换时候Activity的生命周期变化
 *
 * (1)、不设置Activity的android:configChanges时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次
 *
 * (2)、设置Activity的android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次
 *
 * (3)、设置Activity的android:configChanges="orientation|keyboardHidden"时，切屏不会重新调用各个生命周期，只会执行onConfigurationChanged()方法
 */
public class ConfigChangesActivity extends AppCompatActivity {
    private final String TAG="zmmm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_changes);
        Log.i(TAG,"--onCreate-->");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"--onStart-->");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"--onResume-->");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"--onPause-->");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"--onStop-->");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"--onRestart-->");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"--onDestroy-->");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG,"--onNewIntent-->");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"--onSaveInstanceState-->");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"--onRestoreInstanceState-->");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG,"--onConfigurationChanged-->");
    }
}
