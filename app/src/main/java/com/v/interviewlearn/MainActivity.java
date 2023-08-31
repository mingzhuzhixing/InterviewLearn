package com.v.interviewlearn;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.v.module_android_basic.AndroidBasicActivity;
import com.v.module_animator.AnimatorMainActivity;
import com.v.module_calendar.CalendarMainActivity;
//import com.v.module_compose.MainComposeActivity;
import com.v.module_camera.CameraMainActivity;
import com.v.module_flutter.FlutterMainActivity;
import com.v.module_jetpack.JetpackMainActivity;
import com.v.module_optimize.MainOptimizeActivity;
import com.v.module_permission.PermissionMainActivity;
import com.v.module_picker_view.MainPickerViewActivity;
import com.v.module_protobuf.ProtobufMainActivity;
import com.v.module_viewpager.MainViewpagerActivity;
import com.v.module_widget.event_dispatch.EventDispatchActivity;
import com.v.module_bitmap.BitmapMainActivity;
import com.v.module_common.autoservice.IDialogService;
import com.v.module_common.autoservice.IWebViewService;
import com.v.module_common.autoserviceLoader.AutoServiceLoader;
import com.v.module_database.DatabaseMainActivity;
import com.v.module_eventbus.EventBusMainActivity;
//import com.v.module_flutter.FlutterMainActivity;
import com.v.module_glide.GlideMainActivity;
import com.v.module_handler.HandlerMainActivity;
import com.v.module_recyclerview.RecyclerviewMainActivity;
import com.v.module_retrofit.RetrofitMainActivity;
import com.v.module_rxjava.RxJavaMainActivity;
import com.v.module_telephony.TelephonyManagerActivity;
import com.v.module_textview.TextviewMainActivity;
import com.v.module_thread.ThreadMainActivity;
import com.v.module_utils.PackageUtils;
import com.v.module_video.VideoMainActivity;
import com.v.module_widget.CustomViewMainActivity;

import comv.module_network.NetworkMainActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * android 基础
     */
    public void androidBasicClick(View view) {
        startActivity(new Intent(this, AndroidBasicActivity.class));
    }

    /**
     * 进入事件分发
     */
    public void eventDispatch(View view) {
        startActivity(new Intent(this, EventDispatchActivity.class));
    }

    /**
     * 时间选择器
     */
    public void pickerTimeClick(View view) {
        startActivity(new Intent(this, MainPickerViewActivity.class));
    }

    /**
     * 网络请求框架
     */
    public void networkRequest(View view) {
        startActivity(new Intent(this, NetworkMainActivity.class));
    }

    /**
     * RxJava框架
     */
    public void rxJavaFrame(View view) {
        startActivity(new Intent(this, RxJavaMainActivity.class));
    }

    /**
     * Retrofit 框架
     */
    public void retrofitFrame(View view) {
        startActivity(new Intent(this, RetrofitMainActivity.class));
    }

    /**
     * 线程(Thread)/异步
     */
    public void threadClick(View view) {
        startActivity(new Intent(this, ThreadMainActivity.class));
    }

    /**
     * handler通信机制
     */
    public void handlerSignal(View view) {
        startActivity(new Intent(this, HandlerMainActivity.class));
    }

    /**
     * 手写EventBus通信机制
     */
    public void eventBusSignal(View view) {
        startActivity(new Intent(this, EventBusMainActivity.class));
    }

    /**
     * recyclerview
     */
    public void recyclerviewClick(View view) {
        startActivity(new Intent(this, RecyclerviewMainActivity.class));
    }

    /**
     * 数据库
     */
    public void dataBase(View view) {
        startActivity(new Intent(this, DatabaseMainActivity.class));
    }

    /**
     * glide
     */
    public void glideClick(View view) {
        startActivity(new Intent(this, GlideMainActivity.class));
    }

    /**
     * telephony
     */
    public void telephonyClick(View view) {
        startActivity(new Intent(this, TelephonyManagerActivity.class));
    }

    /**
     * bitmap
     */
    public void bitmapClick(View view) {
        startActivity(new Intent(this, BitmapMainActivity.class));
    }

    /**
     * webview
     */
    public void webViewClick(View view) {
        IWebViewService service = AutoServiceLoader.load(IWebViewService.class);
        if (service != null) {
            //service.startWebViewActivity(MainActivity.this, "https://www.baidu.com", "百度", true);
            service.startLocalHtmlActivity(MainActivity.this);
        }
    }

    /**
     * dialog
     */
    public void dialogClick(View view) {
        IDialogService service = AutoServiceLoader.load(IDialogService.class);
        if (service != null) {
            service.startDialogActivity(MainActivity.this);
        }
    }

    /**
     * 视频
     */
    public void videoClick(View view) {
        startActivity(new Intent(this, VideoMainActivity.class));
    }

    /**
     * textview
     */
    public void textviewClick(View view) {
        startActivity(new Intent(this, TextviewMainActivity.class));
    }

    /**
     * 自定义view
     */
    public void customViewClick(View view) {
        startActivity(new Intent(this, CustomViewMainActivity.class));
    }

    /**
     * flutter
     */
    public void flutterClick(View view) {
        startActivity(new Intent(this, FlutterMainActivity.class));
    }


    public void protobufClick(View view) {
        startActivity(new Intent(this, ProtobufMainActivity.class));
    }

    /**
     * 动画
     */
    public void animatorClick(View view) {
        startActivity(new Intent(this, AnimatorMainActivity.class));
    }

    /**
     * 日历控件
     */
    public void calendarClick(View view) {
        startActivity(new Intent(this, CalendarMainActivity.class));
    }

    /**
     * JetPack 组件
     */
    public void jetPackClick(View view) {
        startActivity(new Intent(this, JetpackMainActivity.class));
    }

    /**
     * ViewPager 组件
     */
    public void viewPagerClick(View view) {
        startActivity(new Intent(this, MainViewpagerActivity.class));
    }

    /**
     * 性能优化
     */
    public void performanceOptimizeClick(View view) {
        startActivity(new Intent(this, MainOptimizeActivity.class));
    }

    /**
     * compose 组件
     */
    public void composeClick(View view) {
        //startActivity(new Intent(this, MainComposeActivity.class));
    }

    /**
     * compose 组件
     */
    public void cameraClick(View view) {
        startActivity(new Intent(this, CameraMainActivity.class));
    }

    /**
     * 权限 组件
     */
    public void permissionClick(View view) {
        startActivity(new Intent(this, PermissionMainActivity.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop():" + PackageUtils.isApplicationBroughtToBackground(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause():" + PackageUtils.isApplicationBroughtToBackground(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy():" + PackageUtils.isApplicationBroughtToBackground(this));
    }
}
