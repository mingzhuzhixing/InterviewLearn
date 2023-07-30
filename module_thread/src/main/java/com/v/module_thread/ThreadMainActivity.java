package com.v.module_thread;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_thread.async_task.AsyncTaskActivity;
import com.v.module_thread.blocking_queue.BlockingQueueActivity;

public class ThreadMainActivity extends BaseTitleBarActivity {

    private TextView mTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread_main;
    }

    @Override
    protected String setTitle() {
        return "线程主入口";
    }

    @Override
    public void initData() {
        mTextView = findViewById(R.id.tv_content);

        workThreadUpdateUi();
    }

    /**
     * Android中子线程真的不能更新UI吗？ 不是，极端的情况可以更新的
     * <p>
     * 原因参考：https://blog.csdn.net/xyh269/article/details/52728861
     */
    private void workThreadUpdateUi() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mTextView.setText("你好");
//            }
//        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                mTextView.setText("你好");
//            }
//        }).run();
    }


    public void updateUI(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("你好3");
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mTextView.setText("你好");
//            }
//        }).run();
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