package com.v.service.base_service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.v.interviewlearn.R;
import com.v.service.base_service.MyService.MyBinder;

public class ServiceActivity extends AppCompatActivity {

    MyServiceConnection conn;
    private MyBinder myBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        conn = new MyServiceConnection();
    }

    /**
     * 停止服务
     */
    public void stop(View view) {
        Intent stopIntent = new Intent(this, MyService.class);
        stopService(stopIntent);//停止服务

    }

    /**
     * 开启服务
     */
    public void start(View view) {
        Intent intent = new Intent(ServiceActivity.this, MyService.class);
        startService(intent);
    }

    public void startIntentService(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("info", "good good study");
        startService(intent);
    }

    Intent service=null;
    public void bind(View view) {
        service = new Intent(this, MyService.class);
        bindService(service, conn, Context.BIND_AUTO_CREATE);
    }


    public void unbind(View view) {
        unbindService(conn);
    }

    public void callmethod1(View view) {
        myBinder.callMethod1();
        Log.i(TAG, "syso-------unbind--------service---"+service);
    }
    private static final String TAG = "MyService";



    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "syso---------------onServiceConnected()---");
            myBinder = (MyBinder) service;
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "syso---------------onServiceDisconnected()---");
        }

        @Override
        public void onBindingDied(ComponentName name) {
            Log.i(TAG, "syso---------------onBindingDied()---");

        }

        @Override
        public void onNullBinding(ComponentName name) {
            Log.i(TAG, "syso---------------onNullBinding()---");

        }
    }

}
