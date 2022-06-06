package com.v.module_thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_thread.async_task.AsyncTaskActivity;
import com.v.module_thread.blocking_queue.MessageBean;
import com.v.module_utils.LogUtils;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class ThreadMainActivity extends BaseTitleBarActivity {
    private static final String TAG = "ThreadMainActivity";

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

    /**
     * PriorityBlockingQueue 队列
     * PriorityBlockingQueue是一个支持优先级的无界阻塞队列，可以按照自然排序或自定义排序的顺序在队列中对元素进行排序。
     */
    public void priorityBlockingQueueClick(View view) {
        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>(5);
        Random random = new Random();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int j = random.nextInt(100);
            result.append(j).append(" ");
            queue.offer(j);
        }
        LogUtils.i(TAG, "添加数据: " + result);


        result = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            result.append(queue.poll()).append(" ");
        }
        LogUtils.i(TAG, "打印数据: " + result);
    }

    int index = 0;
    PriorityBlockingQueue<MessageBean> queue;

    public void priorityBlockingQueueClick2(View view) {
//        queue = new PriorityBlockingQueue<MessageBean>(5, new Comparator<MessageBean>() {
//            @Override
//            public int compare(MessageBean o1, MessageBean o2) {
//                return (int) (o1.time - o2.time);
//            }
//        });
        queue = new PriorityBlockingQueue<MessageBean>(5);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int j = random.nextInt(100);
            queue.offer(new MessageBean(index + "----item-" + i, System.currentTimeMillis() / 1000 + j, j));
        }

        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            LogUtils.i(TAG, "打印队列长度: " + queue.size());
            LogUtils.i(TAG, "打印数据: " + queue.poll().toString());
            if (queue.size() == 0) {
                mHandler.removeCallbacksAndMessages(null);
            } else {
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
            return false;
        }
    });

    public void priorityBlockingQueueClick3(View view) {
        index++;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int j = random.nextInt(100);
            queue.offer(new MessageBean(index + "----item-" + i, System.currentTimeMillis() / 1000 + j, j));
        }
    }

    public void stop(View view) {
        mHandler.removeCallbacksAndMessages(null);
    }
}