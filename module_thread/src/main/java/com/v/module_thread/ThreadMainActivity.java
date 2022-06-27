package com.v.module_thread;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_thread.async_task.AsyncTaskActivity;
import com.v.module_thread.blocking_queue.BlockingQueueActivity;
import com.v.module_thread.blocking_queue.MessageBean;
import com.v.module_utils.LogUtils;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class ThreadMainActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread_main;
    }

    @Override
    protected String setTitle() {
        return "线程主入口";
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void processLogical() {

    }

    /**
     * AsyncTask 异步任务
     */
    public void asyncTaskClick(View view) {
        startActivity(AsyncTaskActivity.class);
    }

    public void startPriorityBlockingQueueClick(View view) {
        startActivity(new Intent(this, BlockingQueueActivity.class));
    }
}