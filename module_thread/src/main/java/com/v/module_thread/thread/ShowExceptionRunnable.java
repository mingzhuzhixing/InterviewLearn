package com.v.module_thread.thread;

import android.util.Log;

public class ShowExceptionRunnable implements Runnable {
    private static final String TAG= "Thread";
    private final long MIN_JOB_DURATION_TO_LOG = 6000;

    private boolean mCheckDuration = false;

    private final Runnable mOrigin;
    private Exception mStackTrace;

    public ShowExceptionRunnable(Runnable ori) {
        if (ori == null) {
            throw new NullPointerException("invalid argument: ori=null");
        }

        mOrigin = ori;
        mCheckDuration = true;
        mStackTrace = new Exception("Stack trace of " + ori);
    }

    public ShowExceptionRunnable(Runnable ori, boolean checkDuration) {
        if (ori == null) {
            throw new NullPointerException("invalid argument: ori=null");
        }

        mOrigin = ori;
        mCheckDuration = checkDuration;
        if (mCheckDuration) {
            mStackTrace = new Exception("Stack trace of " + ori);
        }
    }

    @Override
    public void run() {
        long start = mCheckDuration ? System.currentTimeMillis() : 0;
        try {
            mOrigin.run();
        } catch (Throwable e) {
            Log.e(TAG,"++++++++++++++++++ Throwable catched during execution: " + mOrigin, e);
            if (mCheckDuration) {
                Log.e(TAG,"++++++++++++++++++ Job posted in: ", mStackTrace);
            }
        } finally {
            if (mCheckDuration) {
                long end = System.currentTimeMillis();

                if (end - start > MIN_JOB_DURATION_TO_LOG) {
                    Log.e(TAG,"Job: " + mOrigin + " takes too long to complete: " + (end - start) + "ms. Long task should in seperate Thread instead of ThreadPool.\nTask originally created at: ", mStackTrace);
                }
            }

            mStackTrace = null;
        }
    }

    @Override
    public String toString() {
        return "ShowExceptionRunnable: {" + mOrigin.toString() + "}";
    }
}