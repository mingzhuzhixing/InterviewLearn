package com.v.service.base_service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-09 18:52
 */
public class MyIntentService extends IntentService {
    private static final String TAG="MyIntentService";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"---------------onCreate()---");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"---------------onStartCommand()---");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG,"---------------onHandleIntent()---");
        for(int i = 0; i < 100; i++){ //耗时操作
            Log.i(TAG,  i + "--" + Thread.currentThread().getName());
        }
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"---------------onDestroy()---");
        super.onDestroy();
    }
}
