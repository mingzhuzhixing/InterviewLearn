package com.v.module_thread.blocking_queue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.v.module_thread.R;
import com.v.module_utils.LogUtils;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class BlockingQueueActivity extends AppCompatActivity {
    private static final String TAG = "BlockingQueueActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocking_queue);
    }

    public void priorityBlockingQueueClick(View view) {
        PseudoCommentHelper.getInstance().addData(10);
    }

    public void priorityBlockingQueueClick2(View view) {
        PseudoCommentHelper.getInstance().addData(10);
    }

    public void priorityBlockingQueueClick3(View view) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PseudoCommentHelper.getInstance().destroy();
    }
}