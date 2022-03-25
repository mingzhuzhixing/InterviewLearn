package com.v.module_eventbus;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_eventbus.bean.Friend;
import com.v.module_eventbus.eventbus.DNEventbus;

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
