package com.v.interviewlearn;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import com.v.bitmap_module.BitmapMainActivity;
import com.v.common_module.autoservice.IDialogService;
import com.v.common_module.autoservice.IWebViewService;
import com.v.common_module.autoserviceLoader.AutoServiceLoader;
import com.v.database_module.DatabaseMainActivity;
import com.v.event_dispatch.EventDispatchActivity;
import com.v.glide_module.GlideMainActivity;
import com.v.tagtextview_module.TagTextViewActivity;
import com.v.textview_module.TextviewMainActivity;
import com.v.video_module.VideoMainActivity;
import com.youshu.eventbus_module.EventBusMainActivity;
import com.youshu.handler_module.HandlerActivity;
import com.youshu.network_module.NetworkMainActivity;
import com.youshu.retrofit_module.RetrofitMainActivity;
import com.youshu.rxjava_module.RxJavaMainActivity;
import com.youshu.snap_helper_module.SnapHelperActivity;
import com.youshu.telephony_module.TelephonyManagerActivity;

import java.util.ServiceLoader;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tv_content);

        workThreadUpdateUi();
    }

    /**
     * Android中子线程真的不能更新UI吗？ 不是，极端的情况可以更新的
     * <p>
     * 原因参考：https://blog.csdn.net/xyh269/article/details/52728861
     */
    private void workThreadUpdateUi() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mTextView.setText("你好");
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mTextView.setText("你好");
//            }
//        }).run();
    }


    public void updateUI(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("你好3");
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mTextView.setText("你好");
//            }
//        }).run();
    }

    /**
     * 进入事件分发
     */
    public void eventDispatch(View view) {
        startActivity(new Intent(this, EventDispatchActivity.class));
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
     * handler通信机制
     */
    public void handlerSignal(View view) {
        startActivity(new Intent(this, HandlerActivity.class));
    }

    /**
     * 手写EventBus通信机制
     */
    public void eventBusSignal(View view) {
        startActivity(new Intent(this, EventBusMainActivity.class));
    }

    /**
     * recyclerview SnapHelper
     */
    public void snapHelper(View view) {
        startActivity(new Intent(this, SnapHelperActivity.class));
    }

    /**
     * TagTextView
     */
    public void tagTextview(View view) {
        startActivity(new Intent(this, TagTextViewActivity.class));
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
}
