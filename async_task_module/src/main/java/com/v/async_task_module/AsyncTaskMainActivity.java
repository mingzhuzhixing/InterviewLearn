package com.v.async_task_module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class AsyncTaskMainActivity extends AppCompatActivity {

    private ProgressBar pb;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_main);

        pb = findViewById(R.id.progressBar);

        myAsyncTask = new MyAsyncTask(pb);
        myAsyncTask.execute();
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
