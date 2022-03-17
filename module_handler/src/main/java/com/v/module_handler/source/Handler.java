package com.v.module_handler.source;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-16 18:17
 */

public class Handler {
    final Looper mLooper;
    MessageQueue mQueue;

    public Handler(){
        /**
         * ？？
         */
        mLooper = Looper.myLooper();

        /**
         * ？？
         */
        mQueue = mLooper.mQueue;
    }

    /**
     * 发送消息
     */
    public void sendMessage(Message message){
        enqueueMessage(message);
    }

    public void enqueueMessage(Message msg){
        msg.target = this;
        mQueue.enqueueMessage(msg);
    }

    /**
     * 分发消息
     * @param msg
     */
    public void dispatchMessage(Message msg){

        handleMessage(msg);
    }


    /**
     * 处理消息
     */
    public void handleMessage(Message msg){

    }
}
