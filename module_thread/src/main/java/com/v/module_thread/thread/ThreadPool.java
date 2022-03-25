package com.v.module_thread.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.v.module_thread.BuildConfig;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private static DynamicThreadPool sDynamicPool = null;
    private static final ScheduledThreadPoolExecutor scheduledPool = new ScheduledThreadPoolExecutor(1);

    private static Handler sUiHandler = null;
    private static HandlerThread sHandlerThread = null;
    private static Handler sWorkerHandler = null;

    private static HandlerThread sLowHandlerThread = null;
    private static Handler sLowWorkerHandler = null;
    public static int CPU_CORES = 2;

    public static void runOnPool(Runnable r) {
        if (BuildConfig.DEBUG) {
            sDynamicPool.execute(new ShowExceptionRunnable(r));
        } else {
            sDynamicPool.execute(r);
        }
    }

    public static Executor getPoolExecutor() {
        return sDynamicPool;
    }

    public static void runOnUi(Runnable r) {
        //此方法禁止加 LOG. 会因为LogTaker调用产生死循环
        sUiHandler.post(r);
    }

    public static void postOnUiDelayed(Runnable r, int delay) {
        sUiHandler.postDelayed(r, delay);
    }

    public static void runOnWorker(Runnable r) {
        if (BuildConfig.DEBUG) {
            sWorkerHandler.post(new ShowExceptionRunnable(r));
        } else {
            sWorkerHandler.post(r);
        }
    }

    public static void postOnWorkerDelayed(Runnable r, int delay) {
        if (BuildConfig.DEBUG) {
            ShowExceptionRunnable r1 = new ShowExceptionRunnable(r);
            sWorkerHandler.postDelayed(r1, delay);
        } else {
            sWorkerHandler.postDelayed(r, delay);
        }
    }

    public static void removeCallback(Runnable r) {
        if (BuildConfig.DEBUG) {
            sWorkerHandler.removeCallbacks(new ShowExceptionRunnable(r));
        }
        sWorkerHandler.removeCallbacks(r);
        sUiHandler.removeCallbacks(r);
    }

    public static void removeUICallback(Runnable r) {
        sUiHandler.removeCallbacks(r);
    }

    public static Looper getWorkerLooper() {
        return sHandlerThread.getLooper();
    }

    public static Looper getLowWorkerLooper() {
        return sLowHandlerThread.getLooper();
    }

    public static Handler getWorkerHandler() {
        return sWorkerHandler;
    }

    /**
     * Schedule a runnable running at fixed rate
     */
    public static ScheduledFuture<?> schedule(Runnable r, long initialDelay, long period, TimeUnit unit) {
        if (BuildConfig.DEBUG) {
            return scheduledPool.scheduleAtFixedRate(new ShowExceptionRunnable(r), initialDelay, period, TimeUnit.SECONDS);
        } else {
            return scheduledPool.scheduleAtFixedRate(r, initialDelay, period, TimeUnit.SECONDS);
        }
    }

    public static void startup() {
        ThreadUtils.ensureUiThread();
        CPU_CORES = Runtime.getRuntime().availableProcessors();
        // standard pool runner
        final int minThreads = 2;
        final int maxThreads = Math.max(4, (int) (CPU_CORES * 1.5));
        sDynamicPool = new DynamicThreadPool(new LinkedBlockingQueue<Runnable>(), minThreads, maxThreads, 0, 3);

        AsyncTask.setDefaultExecutor(sDynamicPool);

        // ui thread runner
        sUiHandler = new Handler();

        // handler based thread runner
        sHandlerThread = new HandlerThread("internal");
        sHandlerThread.setPriority(Thread.NORM_PRIORITY - 1);
        sHandlerThread.start();
        sWorkerHandler = new Handler(sHandlerThread.getLooper());

        sLowHandlerThread = new HandlerThread("sLowHandlerThread");
        sLowHandlerThread.setPriority(Thread.MIN_PRIORITY + 1);
        sLowHandlerThread.start();
        sLowWorkerHandler = new Handler(sLowHandlerThread.getLooper());
    }

    public static void shutdown() {
        sHandlerThread.quit();
        sLowHandlerThread.quit();
    }

    public static void runOnLowWorker(Runnable r) {
        if (BuildConfig.DEBUG) {
            sWorkerHandler.post(new ShowExceptionRunnable(r));
        } else {
            sLowWorkerHandler.post(r);
        }
    }
}
