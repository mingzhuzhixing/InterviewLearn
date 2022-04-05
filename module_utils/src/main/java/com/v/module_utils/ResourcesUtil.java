package com.v.module_utils;

import android.content.Context;
import android.text.TextUtils;

public class ResourcesUtil {

    public static int getNameId(Context context, String resName, String resType) {
        if (context == null) {
            return 0;
        }
        if (TextUtils.isEmpty(resName) || TextUtils.isEmpty(resType)) {
            return 0;
        }
        try {
            return context.getResources().getIdentifier(resName, resType, context.getPackageName());
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getLayout(Context context, String resName) {
        return getNameId(context, resName, "layout");
    }

    public static int getDrawable(Context context, String resName) {
        return getNameId(context, resName, "drawable");
    }

    public static int getString(Context context, String resName) {
        return getNameId(context, resName, "string");
    }

    public static int getId(Context context, String resName) {
        return getNameId(context, resName, "id");
    }

    public static int getStyle(Context context, String resName) {
        return getNameId(context, resName, "style");
    }

    public static int getArray(Context context, String resName) {
        return getNameId(context, resName, "array");
    }

    public static int getColor(Context context, String resName) {
        return getNameId(context, resName, "color");
    }

    public static int getDimen(Context context, String resName) {
        return getNameId(context, resName, "dimen");
    }

    public static int getAnim(Context context, String resName) {
        return getNameId(context, resName, "anim");
    }

    public static int getRaw(Context context, String resName) {
        return getNameId(context, resName, "raw");
    }

    public static int getStyleable(Context context, String resName) {
        return getNameId(context, resName, "styleable");
    }

    public static int[] getStyleableArray(Context context, String resName) {
        return getResourceIDsByName(resName, "styleable", context.getPackageName());
    }

    /**
     * 利用反射，获取int数组格式的资源ID，例如styleable
     */
    private static int[] getResourceIDsByName(String resName, String resType, String packageName) {
        Class clsR = null;
        int[] ids = null;
        try {
            clsR = Class.forName(packageName + ".R");
            Class[] classes = clsR.getClasses();
            Class desClass = null;
            for (int i = 0; i < classes.length; i++) {
                String[] temp = classes[i].getName().split("\\$");
                if (temp.length >= 2) {
                    if (temp[1].equals(resType)) {
                        desClass = classes[i];
                        break;
                    }
                }
            }
            if (desClass != null) {
                ids = (int[]) desClass.getField(resName).get(resName);
            }
        } catch (Exception e) {
        }
        return ids;
    }

}