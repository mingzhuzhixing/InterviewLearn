package com.v.interviewlearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.v.event_dispatch.EventDispatchActivity;
import com.v.network_architecture.TestNetworkActivity;
import com.youshu.rxjava_module.RxJavaMainActivity;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tv_content);

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
//                mTextView.setText("你好3");
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
        startActivity(new Intent(this, TestNetworkActivity.class));
    }

    /**
     * RxJava框架
     */
    public void rxJavaFrame(View view) {
        startActivity(new Intent(this, RxJavaMainActivity.class));
    }

    /**
     * handler通信机制
     */
    public void handlerSignal(View view) {

    }
}
