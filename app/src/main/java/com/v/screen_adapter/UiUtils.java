package com.v.screen_adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class UiUtils {
    //用于得到转台框的高度
    private static  final String idme_class="com.android.internal.R$dimen";
    Context context;

    //标准值 正常情况下应该保存在配置文件中
    public static final  float standard_width=1080f;
    public static final float standard_height=1920f;

    //实际设备信息  480*800 ？*？
    public static float displayMetricsWidth;
    public static float displayMetricsHeight;

    private static UiUtils instance;

    /**
     * 在这里把设备的相关信息初始化
     * @param context
     */
    private UiUtils(Context context){
        this.context=context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        if(this.displayMetricsWidth==0.0f || this.displayMetricsHeight==0.0f){
            //这里可以得到设备的真实宽和高
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            //需要得到状态栏的高度
            int systemBarHeight=getSystemBarHeight(context);

            //横竖屏判断
            if(displayMetrics.widthPixels>displayMetrics.heightPixels){
                //横屏
                this.displayMetricsWidth=(float) displayMetrics.heightPixels;
                this.displayMetricsHeight=(float) (displayMetrics.widthPixels-systemBarHeight);
            }else{
                //竖屏
                this.displayMetricsWidth=(float) displayMetrics.widthPixels;
                this.displayMetricsHeight=(float) (displayMetrics.heightPixels-systemBarHeight);
            }


        }
    }

    private int getSystemBarHeight(Context context) {
        return getValue(context,idme_class,"system_bar_height",48);
    }

    private int getValue(Context context, String idme_class, String system_bar_height, int i) {
        try {
            Class<?> clz=Class.forName(idme_class);
            Object obj=clz.newInstance();
            Field field=clz.getField(system_bar_height);
            int id = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static UiUtils getInstance(Context context){
        if (instance!=null){
            instance=new UiUtils(context.getApplicationContext());
        }
        return instance;
    }
}
