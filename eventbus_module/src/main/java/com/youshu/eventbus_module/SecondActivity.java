package com.youshu.eventbus_module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.youshu.eventbus_module.bean.Friend;
import com.youshu.eventbus_module.eventbus.DNEventbus;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_event_bus);
    }

    public void send(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                EventBus.getDefault().post(new Friend("Alan", 18));
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
                DNEventbus.getDefault().post(new Friend("Alan", 18));
//            }
//        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
