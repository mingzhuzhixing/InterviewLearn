package com.v.thread_pool;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-15 16:50
 */

public class ThreadPoolTest {

    /**
     * 创建一个单线程的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序执行
     */
    private static ExecutorService singThreadPool  = Executors.newSingleThreadExecutor();

    /**
     * 创建一个定长线程池，可控制线程醉倒并发数，超出的线程会在队列中等待
     */
    private static ExecutorService fixedThreadPool = newFixedThreadPool(5);

    /**
     * 创建一个可缓存的线程池，如果线程池长度超过锅里需要，可灵活回收空闲现场，若无可回收，则新建线程
     */
    private static ExecutorService cacheThreadPool = newCachedThreadPool();


    /**
     * 创建一个定长线程池，支持定时以及周期性任务执行。
     */
//    private static ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool();

    private static MyThreadPool myThreadPool = new MyThreadPool(5, 10);



    public static void main(String[] args) {
//        createSingThreadPool();
//        createFixedThreadPool();
//        createCacheThreadPool();
        createCustomThreadPool();
    }

    /**
     * 单一线程池
     */
    private static void createSingThreadPool(){
        for (int i = 0; i < 10; i++) {
            singThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    getTime();
                }
            });
        }
    }

    /**
     * 固定线程池
     */
    private static void createFixedThreadPool(){
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    getTime();
                }
            });
        }
    }

    /**
     * 缓存线程池
     */
    private static void createCacheThreadPool(){
        for (int i = 0; i < 10; i++) {
            cacheThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    getTime();
                }
            });
        }
    }

    /**
     * 自定义线程池
     */
    private static void createCustomThreadPool(){
        for (int i = 0; i < 111; i++) {
            myThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    getTime();
                }
            });
        }
    }


    public static void getTime() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "====" + System.currentTimeMillis());
    }


    static class MyThreadPool {

        private ThreadPoolExecutor mExecutor;

        public MyThreadPool(int corePoolSize, int maximumPoolSize){
            /**
             * 参数corePoolSize：核心线程池大小
             * 参数maximumPoolSize：最大线程池大小
             * 参数keepAliveTime：大于corePoolSize的线程在执行完任何后多少秒被杀死
             * 参数unit：keepAliveTime时间单位
             * 参数workQueue：要执行的任务队列
             * 参数threadFactory：线程工厂，用于创建线程
             *
             * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory)
             *
             * 注意：当线程数大于最大线程池的大小和任务队列的数之和时，会抛出AbortPolicy.rejectedExecution 异常（线程饱和的异常）
             */

            LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<Runnable>(100);
            mExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 60L, TimeUnit.SECONDS, workQueue, new ThreadFactory() {
               private AtomicInteger count = new AtomicInteger(0);

                @Override
                public Thread newThread(@NonNull Runnable r) {
                    return new Thread(r,"Thread---"+count.incrementAndGet());
                }
            });
        }

        public void execute(Runnable runable){
            mExecutor.execute(runable);
        }

    }
}
