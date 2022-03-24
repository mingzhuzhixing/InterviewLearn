package com.v.module_handler.source;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-16 18:18
 */

public class Looper {

    public MessageQueue mQueue;
    public static ThreadLocal<Looper> mThreadLocal=new ThreadLocal<>();

    private Looper(){
        mQueue = new MessageQueue();
    }

    public static void prepare(){
        if(mThreadLocal.get()!=null){
           throw new RuntimeException("Only one looper many");
        }
        mThreadLocal.set(new Looper());
    }

    public static Looper myLooper(){
        return mThreadLocal.get();
    }

    /**
     * 启动looper 让messageQueue  run
     */
    public static void loop(){
        Looper me=myLooper();
        MessageQueue queue=me.mQueue;

        for (;;){
            Message msg = queue.next();
            if (msg != null){
                msg.target.dispatchMessage(msg);
            }
        }
    }
}
