package com.v.service.aidl_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.v.interviewlearn.R;

import aidl.MyAIDLService;

public class AidlServiceActivity extends AppCompatActivity {

    private MyAIDLService aidlService;
    private TextView tvServiceContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_service);
        tvServiceContent=findViewById(R.id.tv_service_content);
    }

    /**
     * 绑定服务
     */
    public void bindService(View view) {
        Intent intent=new Intent();
        intent.setAction("com.v.service.MyService");
        //从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以这里需要设置Service所在服务端的包名
        intent.setPackage("com.v.service");
        bindService(intent,connection,BIND_AUTO_CREATE);
    }


    /**
     * 解绑服务
     */
    public void unbindService(View view) {
        unbindService(connection);
    }

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("MyService","---onServiceConnected----");
            aidlService=MyAIDLService.Stub.asInterface(service);
            try {
                String str=aidlService.getString();
                tvServiceContent.setText(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MyService","---onServiceDisconnected----");
            aidlService = null;
        }
    };
}
