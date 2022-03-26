package com.v.module_thread;
import android.view.View;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_thread.async_task.AsyncTaskActivity;

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
}