package com.v.module_widget.global_view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.ContentViewCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.petterp.floatingx.FloatingX;
import com.petterp.floatingx.assist.helper.AppHelper;
import com.v.module_base.BaseTitleBarActivity;
import com.v.module_utils.DensityUtils;
import com.v.module_utils.ScreenUtils;
import com.v.module_widget.R;

import java.lang.reflect.Method;

/**
 * FloatingX 一个灵活且强大的 免权限 悬浮窗解决方案。
 *
 * https://github.com/Petterpx/FloatingX
 */
public class MusicBarActivity extends BaseTitleBarActivity {
    RelativeLayout rl_main;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_music_bar;
    }

    @Override
    protected String setTitle() {
        return "MusicBarActivity";
    }

    @Override
    protected void initData() {
        super.initData();
        rl_main =findViewById(R.id.rl_main);
    }

    @Override
    protected void processLogical() {
        super.processLogical();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void entrySecondClick(View view) {
        startActivity(new Intent(this, MusicBarSecondActivity.class));
    }

    public void snackBarClick(View view) {
        Snackbar snackbar = Snackbar.make(view, "这是一个snackbar", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        snackbar.show();
    }

    public void popUpWindowClick(View view) {
        openSimplePopUpWindow();
    }

    private void openSimplePopUpWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.window_float_layout,null);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);//是否接收窗口外部触摸事件
        popupWindow.showAtLocation(mFlContainer, Gravity.BOTTOM,0,0);
        //popupWindow.showAtLocation(this.getCurrentFocus(),Gravity.BOTTOM,0,0);
    }

    public void windowManagerClick(View view) {
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        if (manager != null) {
//            WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.TYPE_PHONE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
//                    PixelFormat.TRANSLUCENT);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(-1,4);
            params.x = params.y = 0;
            params.width = ScreenUtils.getWidthPixels(this);
            params.height = DensityUtils.dip2px(this, 100);
            params.gravity = Gravity.START | Gravity.TOP;
            //加载View界面
            View mTopView = LayoutInflater.from(this).inflate(R.layout.window_float_layout, null);
            manager.addView(mTopView, params); //把View添加到WindowManager中
        }
    }

    public void floatingXClick(View view) {
        FloatingX.control().show();
    }
}