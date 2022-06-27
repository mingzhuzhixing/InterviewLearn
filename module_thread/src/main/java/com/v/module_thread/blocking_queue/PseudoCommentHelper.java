package com.v.module_thread.blocking_queue;


import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: PseudoComment
 * Description: 伪评论
 *
 * @author zhuming
 * @package_name com.youshu.module_live.tengcentIm
 * @date 2022/6/6 16:58
 */
public class PseudoCommentHelper {
    private final String TAG = "live";

    //队列 volatile
    private volatile PriorityBlockingQueue<TencentImBean> mQueueList;
    private volatile LinkedList<TencentImBean> mLinkedList;

    private Disposable disposable;

    private PseudoCommentHelper() {
        mQueueList = new PriorityBlockingQueue<>(11, new Comparator<TencentImBean>() {
            @Override
            public int compare(TencentImBean o1, TencentImBean o2) {
                return (int) (o1.send_time - o2.send_time);
            }
        });
        mLinkedList = new LinkedList<>();
    }

    public static PseudoCommentHelper getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final PseudoCommentHelper instance = new PseudoCommentHelper();
    }

    /**
     * 添加队列消息
     */
    public void addMessage(List<TencentImBean> msgList) {
        long currentTime = getCurrentSeconds(); //秒值
        //判断当前时间大于等于消失时间
        for (TencentImBean bean : msgList) {
            long diff = bean.send_time - currentTime;
            if (diff >= -5) {
                mQueueList.offer(bean);
                mLinkedList.add(bean);
            } else {
                //抛弃小于当前时间5s的消息
            }
        }
        Collections.sort(mLinkedList, new Comparator<TencentImBean>() {
            @Override
            public int compare(TencentImBean o1, TencentImBean o2) {
                return (int) (o1.send_time - o2.send_time);
            }
        }); // 排序

        Iterator<TencentImBean> iterator = mQueueList.iterator();
        while (iterator.hasNext()) {
            TencentImBean next = iterator.next();
            printLog(Log.WARN, TAG + " addMessage", "text:" + next.text + " nick:" + next.nick + " send_time:" + next.send_time + " currentTime:" + currentTime + " size:" + mQueueList.size());
        }
        if (mQueueList.size() > 0) {
            startTimerDown();
            //sendMessage();
        }
    }

    /**
     * 出栈队列消息
     */
    public void sendMessage() {
        try {
            long currentTime = getCurrentSeconds(); //秒值
            printLog(Log.ERROR, TAG + " sendMessage", "---------------------------------------- :"+mLinkedList.size());
            List<TencentImBean> tempMsg = new ArrayList<>();
//            for (TencentImBean nextMsg : mLinkedList) {
//                long sendTime = nextMsg.send_time;
//                //printLog(Log.VERBOSE, TAG + " sendMessage", "text:" + nextMsg.text + " nick:" + nextMsg.nick + " send_time:" + sendTime + " currentTime:" + currentTime + " size:" + mQueueList.size());
//                if (sendTime <= currentTime) {
//                    tempMsg.add(nextMsg);
//                    mQueueList.remove(nextMsg);
//                    if (nextMsg != null) {
//                        printLog(Log.ERROR, TAG + " sendMessage", " " + nextMsg.text + " " + nextMsg.nick + " " + currentTime + " " + sendTime + " " + mQueueList.size() + " " + nextMsg.quote);
//                        //TencentImClient.getInstance().handlerRecvGroupCustomMessage(JSON.toJSONString(msg));
//                    }
//                }
//            }
            Iterator<TencentImBean> iterator = mLinkedList.iterator();
            while (iterator.hasNext()) {
                TencentImBean nextMsg = iterator.next();
                long sendTime = nextMsg.send_time;
                //printLog(Log.VERBOSE, TAG + " sendMessage", "text:" + nextMsg.text + " nick:" + nextMsg.nick + " send_time:" + sendTime + " currentTime:" + currentTime + " size:" + mQueueList.size());
                if (sendTime <= currentTime) {
                    tempMsg.add(nextMsg);
                    mQueueList.remove(nextMsg);
                    if (nextMsg != null) {
                        printLog(Log.ERROR, TAG + " sendMessage", " " + nextMsg.text + " " + nextMsg.nick + " " + currentTime + " " + sendTime + " " + mQueueList.size() + " " + nextMsg.quote);
                    }
                }
            }
            if (tempMsg.size() > 0) {
                mLinkedList.removeAll(tempMsg);
            }
//            printLog(Log.ERROR, TAG + " sendMessage", "=============================================== ");
//            Iterator<TencentImBean> iterator = mQueueList.iterator();
//            while (iterator.hasNext()) {
//                TencentImBean nextMsg = iterator.next();
//                long sendTime = nextMsg.send_time;
//                printLog(Log.VERBOSE, TAG + " sendMessage", "text:" + nextMsg.text + " nick:" + nextMsg.nick + " send_time:" + sendTime + " currentTime:" + currentTime + " size:" + mQueueList.size());
//                if (sendTime <= currentTime) {
//                    TencentImBean msg = mQueueList.poll();
//                    if (msg != null) {
//                        printLog(Log.ERROR, TAG + " sendMessage", " " + msg.text + " " + msg.nick + " " + currentTime + " " + sendTime + " " + mQueueList.size() + " " + msg.quote);
//                        //TencentImClient.getInstance().handlerRecvGroupCustomMessage(JSON.toJSONString(msg));
//                    }
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启定时器 200毫秒 循环一次
     * initialDelay 第一次执行的延迟时间
     * period 每次执行的间隔的时间
     * unit 时间单位
     */
    private void startTimerDown() {
        if (disposable != null && !disposable.isDisposed()) {
            return;
        }
        disposable = Observable.interval(1, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        //printLog(Log.WARN, TAG + " startTimerDown", " aLong:{}" + aLong + " currenTtime:" + getCurrentSeconds());
                        try {
                            if (mQueueList.isEmpty()) {
                                if (disposable != null && !disposable.isDisposed()) {
                                    disposable.dispose();
                                }
                            } else {
                                //发送消息
                                sendMessage();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 获取当前秒值
     */
    private long getCurrentSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 打印log
     */
    public void printLog(int type, String tag, String msg) {
        switch (type) {
            case Log.DEBUG:
                Log.d(tag, msg);
                break;
            case Log.WARN:
                Log.w(tag, msg);
                break;
            case Log.ERROR:
                Log.e(tag, msg);
                break;
            case Log.VERBOSE:
                Log.v(tag, msg);
                break;
            default:
                Log.i(tag, msg);
                break;
        }
    }

    /**
     * 销毁
     */
    public void destroy() {
        try {
            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
                disposable = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (mQueueList != null && mQueueList.size() > 0) {
                mQueueList.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (disposable2 != null && !disposable2.isDisposed()) {
                disposable2.dispose();
                disposable2 = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //----------------------------------mock数据后期删除---------------------------------
    private Disposable disposable2;

    public void start(int size) {
//        disposable2 = Observable.interval(1, 5000, TimeUnit.MILLISECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        addData();
//                    }
//                });

        addData(size);
    }

    private int count = 0;
    private int task_id = 0;
    private String fake_task_id;

    public void addData(int size) {
        List<TencentImBean> fakeMessage = new ArrayList<>();
        long currentTime = getCurrentSeconds(); //秒值
        task_id++;
        fake_task_id = (420 + task_id) + "";
        for (int i = 0; i < size; i++) {
            count++;
//            fakeMessage.add(getBean("item" + count + "_" + fake_task_id, "有书~" + count, fake_task_id, currentTime + i));
            fakeMessage.add(getBean("item--" + count + "-" + (currentTime + i), "有书~" + count, fake_task_id, currentTime + i));
        }
        if (fakeMessage.size() > 0) {
            addMessage(fakeMessage);
        }
    }

    private TencentImBean getBean(String text, String nick, String task_id, long send_time) {
        TencentImBean bean = new TencentImBean();
        bean.text = text;
        bean.type = "text";
        bean.nick = nick;
        bean.avatar = "http://avatar.youshu.cc/ys_default.png";
        bean.userID = 90000000;
        bean.role = "MEMBER";
        bean.img = "";
        bean.action = "sendFakeUserLiveMsg";
        bean.send_time = send_time;
        bean.source = "4";
        bean.quote = "{\"fake_task_id\":" + task_id + ",\"create_time\":\"2022-06-16 11:18:34\",\"membership_icon\":\"\",\"user_type_icon\":\"\",\"level_icon\":\"new_shutong_tag\",\"start\":\"2022-06-16 03:18:30\",\"key\":1,\"send_time\":\"2022-06-16 03:18:30\"}";
        bean.isHidden = false;
        bean.msgId = "1537273399380238336";
        return bean;
    }
}
