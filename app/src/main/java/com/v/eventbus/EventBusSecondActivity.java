package com.v.eventbus;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.v.interviewlearn.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusSecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_second);
    }

    public void send(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post("123");
            }
        }).start();
    }
}
