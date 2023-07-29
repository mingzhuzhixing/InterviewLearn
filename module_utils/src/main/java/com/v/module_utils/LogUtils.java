package com.v.module_utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * ClassName: LogUtils
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_utils
 * @date 2022/6/2 19:07
 */

public class LogUtils {
    //可以全局控制是否打印log日志
    private static final boolean isEnableLog = BuildConfig.DEBUG;
    private static final int LOG_MAX_LENGTH = 4000;
    private static final String TAG = LogUtils.class.getSimpleName();

    private static final int V = Log.VERBOSE;
    private static final int D = Log.DEBUG;
    private static final int I = Log.INFO;
    private static final int W = Log.WARN;
    private static final int E = Log.ERROR;
    private static final boolean isPrintStack = true;

    public static void i(String msg) {
        if (isPrintStack) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            printLog(I, TAG, msg, stack[1]);
        } else {
            printLog(I, TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isPrintStack) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            printLog(I, tag, msg, stack[1]);
        } else {
            printLog(I, tag, msg);
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        printLog(D, tag, msg);
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        printLog(W, tag, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        printLog(E, tag, msg);
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        printLog(V, tag, msg);
    }

    private static void printLog(int type, String tag, String msg) {
        if (!isEnableLog) {
            return;
        }
        printLog(type, tag, msg, null);
    }

    private static void printLog(int type, String tag, String msg, StackTraceElement ste) {
        //判断tag值
        tag = TextUtils.isEmpty(tag) ? TAG : tag;

        if (msg.length() > LOG_MAX_LENGTH) {
            while (msg.length() > LOG_MAX_LENGTH) {
                print(type, tag, msg.substring(0, LOG_MAX_LENGTH), ste);
                msg = msg.substring(LOG_MAX_LENGTH);
            }
        }
        print(type, tag, msg, ste);
    }

    private static void print(int type, String tag, String msg, StackTraceElement ste) {
        switch (type) {
            case I:
                if (ste != null) {
                    Log.i(tag, String.format("[%s][%s]%s[%s]", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), msg));
                } else {
                    Log.i(tag, msg);
                }
                break;
            case D:
                Log.d(tag, msg);
                break;
            case W:
                Log.w(tag, msg);
                break;
            case V:
                Log.v(tag, msg);
                break;
            case E:
                Log.e(tag, msg);
                break;
        }
    }
}