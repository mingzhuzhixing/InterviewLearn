package com.v.service.base_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-09 16:59
 */
public class MyService extends Service {
    private static final String TAG="MyService";
    private int serviceStartId;

    public int getServiceStartId() {
        return serviceStartId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"syso---------------onCreate()---");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG,"syso---------------onStart()---");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        stopSelf();


        int result=super.onStartCommand(intent, flags, startId);
        serviceStartId=startId;
        Log.i(TAG,"syso---------------onStartCommand()--result--:"+result+"----startId----:"+startId);
        return result;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"syso---------------onDestroy()---");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"syso---------------onBind()---");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"syso---------------onUnbind()---");
        return super.onUnbind(intent);
    }


    public class MyBinder extends Binder {
        public void callMethod1(){
            Log.i(TAG,"syso---------------callMethod1()---");
        }

        public void callMethod2(){

        }
    }

}
