package com.youshu.handler_module.source;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-16 18:18
 *
 *
 */

public class MessageQueue {

    /**
     * 实现仓库的阻塞队列
     */
    BlockingQueue<Message> queue;

    /**
     * 仓库大小
     */
    private static final int MAXCOUNT= 10;

    public MessageQueue(){
        queue = new ArrayBlockingQueue<>(MAXCOUNT);
    }

    /**
     * 向消息对列中添加消息
     */
    public void enqueueMessage(Message msg){
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从消息队列中取出消息
     */
    public Message next(){
        Message msg = null;
        try {
            msg = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
