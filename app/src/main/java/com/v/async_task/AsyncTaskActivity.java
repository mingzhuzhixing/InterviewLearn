package com.v.async_task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.v.interviewlearn.R;

public class AsyncTaskActivity extends AppCompatActivity {

    private ProgressBar pb;
    private MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

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
