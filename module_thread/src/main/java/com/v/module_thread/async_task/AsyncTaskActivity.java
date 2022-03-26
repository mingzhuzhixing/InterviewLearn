package com.v.module_thread.async_task;

import android.widget.ProgressBar;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_thread.R;

public class AsyncTaskActivity extends BaseTitleBarActivity {
    private MyAsyncTask myAsyncTask;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_async_task;
    }

    @Override
    protected String setTitle() {
        return "AsyncTask 异步任务";
    }

    @Override
    protected void initData() {
        ProgressBar pb = findViewById(R.id.progressBar);
        myAsyncTask = new MyAsyncTask(pb);
        myAsyncTask.execute();
    }

    @Override
    protected void processLogical() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        //当activity不可见时将asyncTask标记为取消状态
        if(myAsyncTask != null && myAsyncTask.getStatus() == MyAsyncTask.Status.RUNNING){
            myAsyncTask.cancel(true);
        }
    }
}
