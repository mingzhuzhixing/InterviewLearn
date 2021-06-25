package com.youshu.handler_module;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class HandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        Toast.makeText(HandlerActivity.this, "ceshi", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(HandlerActivity.this, "ceshi123", Toast.LENGTH_LONG).show();
                    }
                };

                String name = mHandler.getLooper().getThread().getName();
                Log.i("tag","name-111-->"+name);
                mHandler.sendMessage(new Message());
            }
        }.start();
    }
}
