package com.v.module_widget.global_view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.WindowManager;

public class FloatWindowManager {

    public static FloatView floatView;

    public static WindowManager.LayoutParams floatParams;

    private static WindowManager windowManager;

    private OnFloatViewClickListener clickListener;

    public static void creatFloatWindow(Context context, OnFloatViewClickListener listener) {
        windowManager = getWindowManager(context);
        if (floatView == null) {
            floatView = new FloatView(context);
            if (floatParams == null) {
                floatParams = new WindowManager.LayoutParams();
                floatParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                floatParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
                floatParams.x = 15;
                floatParams.y = 170;
                floatParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//              不加这一句，会出现悬浮按钮有黑边框
                floatParams.format = PixelFormat.RGBA_8888;
                floatParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                floatParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    floatParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                } else {
                    floatParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                }
            }
            floatView.setLayoutParams(floatParams);
            floatView.setClickListener(listener);
            windowManager.addView(floatView, floatParams);
        }
    }

    /**
     * 移除悬浮窗
     */
    public static void removeFloatView(Context context) {
        if (floatView != null) {
            WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(floatView);
            floatView = null;
        }
    }

    /**
     * 如果WindowManager还未创建，则创建一个新的WindowManager返回。否则返回当前已创建的WindowManager。
     *
     * @param context 必须为应用程序的Context.
     * @return WindowManager的实例，用于控制在屏幕上添加或移除悬浮窗。
     */
    private static WindowManager getWindowManager(Context context) {
        if (windowManager == null) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    /**
     * 是否有悬浮框
     *
     * @return
     */
    public static boolean hasFloatWindow() {
        return floatView == null ? false : true;
    }
}
