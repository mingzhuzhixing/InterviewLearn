package com.v.module_handler;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.v.module_handler.weakreference.BaseHandler;

public class HandlerMainActivity extends AppCompatActivity {
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_main);
        tvTitle = findViewById(R.id.tv_title);
    }

    public void clickButton(View view) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Handler mHandler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Toast.makeText(HandlerMainActivity.this, "ceshi123", Toast.LENGTH_LONG).show();
                    }
                };

                String name = mHandler.getLooper().getThread().getName();
                Log.i("tag", "name-111-->" + name);
                mHandler.sendMessage(new Message());
            }
        }.start();
    }

    //static 一定要加
    private static class MyHandler extends BaseHandler<HandlerMainActivity> {
        int count = 0;

        public MyHandler(HandlerMainActivity handlerActivity) {
            super(handlerActivity);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void handleMessages(HandlerMainActivity handlerActivity, Message msg) {
            count += 1;
            handlerActivity.tvTitle.setText(String.format("更新内容：%d", count));
        }
    }

    private final MyHandler myHandler = new MyHandler(this);

    /**
     * 弱引用发型消息
     */
    public void weakReferenceClick(View view) {
        myHandler.sendEmptyMessage(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.release();
    }
}
