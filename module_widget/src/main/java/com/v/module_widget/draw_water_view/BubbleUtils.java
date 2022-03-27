package com.v.module_widget.draw_water_view;

import android.content.Context;
import android.graphics.PointF;

import com.v.module_utils.DensityUtils;

public class BubbleUtils {

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        //获取status_bar_height资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源id获取相应的尺寸值
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return DensityUtils.dip2px(context, 25);
    }

    public static PointF getPointByPercent(PointF p1, PointF p2, float percent) {
        return new PointF(evaluateValue(percent, p1.x, p2.x), evaluateValue(percent, p1.y, p2.y));
    }

    /**
     * 根据分度值，计算冲start到end中，fraction位置的值，fraction 范围为0 ~ 1
     *
     * @param fraction 比率
     * @param start    开始
     * @param end      结束
     */
    public static float evaluateValue(float fraction, Number start, Number end) {
        //star ==10 end ==2
        //fraction =0.5
        //result = 10+(-8)*fraction=0.36
        return start.floatValue() + (end.floatValue() - start.floatValue()) * fraction;
    }
}
