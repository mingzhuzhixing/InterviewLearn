package com.v.async_task;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ProgressBar;

import java.net.URL;

public class MyAsyncTask extends AsyncTask<URL, Integer, Long> {
    private final String TAG = this.getClass().getSimpleName();

    private ProgressBar mProgressBar;

    public MyAsyncTask(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    /**
     * 任务执行开始前，主线程中
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(TAG, "--onPreExecute()--");
    }


    /**
     * AsynckTask的抽象方法，任务执行中，在子线各执行
     */
    @Override
    protected Long doInBackground(URL... urls) {
        Log.i(TAG, "--doInBackground()--");
        for (int i = 0; i < 100; i++) {
            if (isCancelled()) {
                return null;
            }
            SystemClock.sleep(500);
            publishProgress(i);//当调用此方法时，系统才会调用onProgressUpdate方法，参数即是onProgressUpdate的参数
        }

        return null;
    }


    /**
     * 执行中，主线程中
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i(TAG, "--onProgressUpdate()-->"+values[0]);
        if (isCancelled()) {
            return;
        }
        mProgressBar.setProgress(values[0]);
    }


    /**
     * 执行完成，在主线程中
     */
    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        Log.i(TAG, "--onPostExecute()--");
    }

    /**
     * 用于取消执行任务中正在更新的UI
     */
    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.i(TAG, "--onCancelled()--");
    }
}
