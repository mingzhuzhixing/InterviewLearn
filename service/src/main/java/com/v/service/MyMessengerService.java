package com.v.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;


public class MyMessengerService extends Service {
    private static String TAG="MyMessengerService";

    @SuppressLint("HandlerLeak")
    private Messenger messenger=new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 123:
                    Log.i(TAG,"HELLO WORLD");
            }
        }
    });




    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
