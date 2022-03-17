package com.v.module_handler.weakreference;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 问题：Memory Leak的根本原因是Handler持有Context，导致Context无法释放。那么解决的办法就是断开Handler和Context之间的连接，让Handler不再持有Context的引用。
 * 回答：
 * 1、将隐性匿名类Handler变成static的内部类，由于static的内部类，使用的使用不需要外部类的实例，所以static的内部类和外部类是没有联系的，从而不持有外部类的引用；
 * 2、将隐性匿名类写成一个单独的类（top-level-class），这样Handler和Context之间就没有联系了。
 * <p>
 * 注意：继承该基类 一定要是static的类
 */
public abstract class BaseHandler<T> extends Handler {
    private final WeakReference<T> weakReference;

    public BaseHandler(T t) {
        weakReference = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        try {
            T t = weakReference.get();
            if (t == null) {
                return;
            }
            handleMessages(t, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void handleMessages(T t, Message msg);

    /**
     * 释放handler
     */
    public void release() {
        this.removeCallbacksAndMessages(null);
    }
}
