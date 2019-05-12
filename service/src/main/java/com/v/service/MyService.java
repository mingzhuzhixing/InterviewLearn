package com.v.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import aidl.MyAIDLService;

public class MyService extends Service {
    private static String TAG="MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"---onCreate----");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"---onStartCommand----");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"---onDestroy----");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"---onBind----");
        return new MyBind();
    }

    class MyBind extends MyAIDLService.Stub{

        @Override
        public String getString() throws RemoteException {
            String string = "我是从服务起返回的";
            Log.i(TAG,"---getString----");
            return string;
        }
    }
}
