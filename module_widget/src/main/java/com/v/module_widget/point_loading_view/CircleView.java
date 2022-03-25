package com.v.module_widget.point_loading_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * ClassName: CircleView
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_widget.point_loading_view
 * @date 2022/3/25 11:34 上午
 */
public class CircleView extends View {
    private final Paint mPaint;

    public CircleView(Context context, int color) {
        this(context, null, color);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int color) {
        this(context, attrs, 0, color);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int color) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆 float cx, float cy, float radius, @NonNull Paint paint
        float cx = Math.min(getWidth(), getHeight()) / 2.0f;
        canvas.drawCircle(cx, cx, cx, mPaint);
    }

    /**
     * 改变颜色
     */
    public void exchangeColor(int color) {
        this.mPaint.setColor(color);
        invalidate();
    }

    /**
     * 获取单签画笔的颜色
     */
    public int getCurrentColor(){
        return mPaint.getColor();
    }
}
