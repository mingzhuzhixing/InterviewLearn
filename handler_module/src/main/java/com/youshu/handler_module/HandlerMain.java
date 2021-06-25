package com.youshu.handler_module;

import com.youshu.handler_module.source.Handler;
import com.youshu.handler_module.source.Looper;
import com.youshu.handler_module.source.Message;

import java.util.UUID;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-16 18:17
 */

public class HandlerMain {

    public static void main(String[] args) {
        Looper.prepare();

        final Handler mHandler= new Handler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println("Thread id:"+ Thread.currentThread().getName() + " received message:"+msg.obj);
            }
        };

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//              while (true){
//                  Message msg= new Message();
//                  msg.obj = UUID.randomUUID().toString();
//                  System.out.println(Thread.currentThread().toString()+ " send message:"+msg.obj);
//                  mHandler.sendMessage(msg);
//
//                  try {
//                      Thread.sleep(500);
//                  }catch (Exception e){
//                      e.printStackTrace();
//                  }
//              }
//            }
//        }).start();

        for (int i = 0; i < 10; i++){
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        String str;
                        synchronized (UUID.class){
                            str= UUID.randomUUID().toString();
                        }

                        Message msg= new Message();
                        msg.obj = str;
                        System.out.println(Thread.currentThread().toString()+ " send message:"+msg.obj);
                        mHandler.sendMessage(msg);
                        try {
                            Thread.sleep(500);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }

        Looper.loop();
    }

    /**
     * 总结
     *
     * 子线程真的不能更新UI吗？
     * 生产者---消费者设计模式懂了吗？
     * Handler是如何完成自信和主线程通信
     * ThreadLocal 原理面试还有问题吗？
     * 享元设计模式有用到了吗？  用到了 ，message的初始化，使用obtain(),服用message
     *
     *
     *
     */

}
