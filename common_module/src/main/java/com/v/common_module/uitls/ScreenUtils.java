package com.v.common_module.uitls;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ClassName: ScreenUtil
 * Description: 屏幕尺寸工具
 *
 * @author wangbingqi
 * @package_name com.youshu.module_common
 * @date 2021/10/19 10:47 AM
 */
public class ScreenUtils {
    private static float sDensity = 0f;
    private static int sDensityDpi = 0;
    private static int sScreenWidth = 0;
    private static int sScreenHeight = 0;

    public static int getScreenWidthPixels(Context context) {
        if (sScreenWidth == 0) {
            initScreenHeightWidth(context);
        }
        return sScreenWidth;
    }


    private static synchronized void initScreenHeightWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(dm);
        sScreenWidth = dm.widthPixels;
        sScreenHeight = dm.heightPixels;
        if (sScreenHeight < sScreenWidth) {
            int t = sScreenHeight;
            sScreenHeight = sScreenWidth;
            sScreenWidth = t;
        }
    }

    /**
     * 获取 android 通知栏高度
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {

        }
        return statusBarHeight;
    }


    private static final String TAG = "ScreenUtil";

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕的宽度
     */
    public static int getWidthPixels(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度
     */
    public static int getHeightPixels(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        Log.e("kkk", "屏幕高度==========》" + dm.heightPixels);
        return dm.heightPixels;
    }

    /**
     * 获取屏幕的高度,包含顶部状态栏
     */
    public static int getRealHeightPixelsWithNoNavigation(Activity context) {
        return getRealHeightPixels(context) - getNavigationBarHeight2(context);
    }

    /**
     * 获取屏幕的高度，包含状态栏和导航栏
     */
    public static int getRealHeightPixels(Activity context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
//        Log.e("kkk", "屏幕真实高度==========》" + dm.heightPixels);
            return dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /* 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight1(Context context) {
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                    "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //获取虚拟按键的高度
    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        try {
            Resources res = context.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //获取虚拟按键的高度，全面屏下返回0，非全面屏下返回实际高度
    public static int getNavigationBarHeight2(Activity context) {
        int result = 0;
        try {
            if (hasNavBar2(context)) {
                Resources res = context.getResources();
                int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    result = res.getDimensionPixelSize(resourceId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     */
    @Deprecated
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 检查是否存在虚拟按键栏,以activity和screen高度做判断
     */
    private static boolean hasNavBar2(Activity context) {
        if (context == null) {
            return false;
        }
        Rect rect = new Rect();
        try {
            context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int activityHeight = rect.height();
        int screenHeight = getRealHeightPixels(context);
        return activityHeight == (screenHeight - getStatusBarHeight1(context) - getNavigationBarHeight(context));
    }

    /**
     * 判断虚拟按键栏是否重写
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sNavBarOverride;
    }

    public static void fullScreen(@NonNull Context context, boolean full) {
        if (full) {
            if (Build.VERSION.SDK_INT < 19) {
                View v = ((Activity) context).getWindow().getDecorView();
                v.setSystemUiVisibility(View.GONE);
            } else {
                //for new api versions.
                View decorView = ((Activity) context).getWindow().getDecorView();
                ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                decorView.setSystemUiVisibility(uiOptions);
            }
        } else {
            if (Build.VERSION.SDK_INT < 19) {
                View v = ((Activity) context).getWindow().getDecorView();
                v.setSystemUiVisibility(View.VISIBLE);
            } else {
                //for new api versions.
                ((Activity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                View decorView = ((Activity) context).getWindow().getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }

    public static float getDimension(Context context, @DimenRes int id) {
        try {
            return context.getApplicationContext().getResources().getDimension(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
