/**
 * Copyright (c) Tapas Mobile.  All Rights Reserved.
 *
 * @author DaiHui
 * @version 1.0
 */

package com.v.thread_module.thread;

import android.os.Looper;

public class ThreadUtils {

    public static void ensureUiThread() {
        if (!ThreadUtils.isUiThread()) {
            throw new IllegalStateException("ensureUiThread: thread check failed");
        }
    }

    public static void ensureNonUiThread() {
        if (ThreadUtils.isUiThread()) {
            throw new IllegalStateException("ensureNonUiThread: thread check failed");
        }
    }

    public static boolean isUiThread() {
        final Looper myLooper = Looper.myLooper();
        final Looper mainLooper = Looper.getMainLooper(); // never null

        return mainLooper.equals(myLooper);
    }

}
