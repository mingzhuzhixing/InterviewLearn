package com.v.service.messenger;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.v.interviewlearn.R;

public class DoubleMessengerServiceActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    private Messenger messenger=new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msgFromServer) {
            super.handleMessage(msgFromServer);

            switch (msgFromServer.what){
                case 456:

                    Bundle bundle=msgFromServer.getData();
                    String nickname= bundle.getString("nickname");
                    boolean b=bundle.getBoolean("is_login");
                    int id=bundle.getInt("user_id");
                    Log.i("MyDoubleMessenger","nickname--:"+nickname+"---b--->"+b+"----id--->"+id);

                    break;
            }

        }
    });

    boolean   isConn = false;

    private Messenger mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_messenger_service);
    }


    public void bindBackService(View view) {
        Intent intent=new Intent();
        intent.setAction("com.v.service.MyDoubleMessengerService");
        intent.setPackage("com.v.service");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    public void unbindService(View view) {
        unbindService(connection);
    }

    public void sendMessage(View view) {
        Message msgFromClient=new Message();
        msgFromClient.what=123;
        msgFromClient.replyTo = messenger;
        if(isConn){
            try {
                mService.send(msgFromClient);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            ToastUtils.showShort("没有邦定服务");
        }
    }


    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConn=true;
            mService=new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConn=false;
        }
    };
}
