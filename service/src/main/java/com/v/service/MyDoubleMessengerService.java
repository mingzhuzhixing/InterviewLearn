package com.v.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;


public class MyDoubleMessengerService extends Service {
    private static String TAG = "MyDoubleMessengerService";

    @SuppressLint("HandlerLeak")
    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgfromClient) {
            super.handleMessage(msgfromClient);

            Message msgToClient = Message.obtain(msgfromClient);//返回给客户端的消息
            switch (msgfromClient.what) {
                case 123:
                    Log.i(TAG, "HELLO WORLD");
                    try {
                        //传递数据
                        Bundle toClicentDate = new Bundle();
                        toClicentDate.putString("nickname", "张小可");
                        toClicentDate.putBoolean("is_login", true);
                        toClicentDate.putInt("user_id", 10086);

                        msgToClient.setData(toClicentDate);
                        msgToClient.what = 456;

                        //传回Client
                        msgfromClient.replyTo.send(msgToClient);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    });


    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
