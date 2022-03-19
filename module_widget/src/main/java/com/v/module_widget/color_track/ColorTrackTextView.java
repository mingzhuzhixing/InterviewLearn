package com.v.module_widget.color_track;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.v.module_widget.R;

/**
 * 滑动字体颜色跟踪变色
 */
public class ColorTrackTextView extends AppCompatTextView {
    private Paint mOriginPaint;
    private Paint mChangePaint;

    //进度
    private float mCurrentProgress = 0.0f;

    //实现不同方向
    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT;
    }

    private Direction mDirection = Direction.LEFT_TO_RIGHT;


    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    /**
     * 初始化画笔
     */
    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        //原始字体颜色
        int originColor = array.getColor(R.styleable.ColorTrackTextView_originColor, Color.BLACK);
        //滑动后改变的颜色
        int changeColor = array.getColor(R.styleable.ColorTrackTextView_changeColor, Color.RED);
        array.recycle();

        mOriginPaint = getPaintByColor(originColor);
        mChangePaint = getPaintByColor(changeColor);
    }

    /**
     * 根据
     * 颜色获取画笔
     */
    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);//抗锯齿
        paint.setDither(true); //防抖动
        paint.setTextSize(getTextSize());//设置字体大小 这里使用系统的设置字体大小
        return paint;
    }

    /**
     * 利用clipRect的api 可以裁剪 左边用一个画笔去画，右边用另一个画笔去画 不断改变中间值
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas); 不适用系统的，自己实现

        //根据进度计算出中间值
        int middle = (int) (mCurrentProgress * getWidth());
        String text = getText().toString();

        if (mDirection == Direction.LEFT_TO_RIGHT) {
            //向左滑--->左边红色  右边黑色
            leftToRight(canvas, text, middle);
        } else {
            //向左滑--->左边黑色  右边红色
            rightToLeft(canvas, text, middle);
        }
    }

    private void leftToRight(Canvas canvas, String text, int middle) {
        canvas.save();
        //canvas.clipRect();//裁剪区域
        //绘制不变色的
        Rect rect = new Rect(0, 0, middle, getHeight());
        canvas.clipRect(rect);
        //获取自己的宽度
        Rect bounds = new Rect();
        mChangePaint.getTextBounds(text, 0, text.length(), bounds);
        int dx = getWidth() / 2 - bounds.width() / 2;
        Paint.FontMetricsInt fontMetrics = mChangePaint.getFontMetricsInt();
        int baseLine = getHeight() / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText(text, dx, baseLine, mChangePaint);  //这么花其实还是只有一种颜色
        canvas.restore();

        canvas.save();
        //绘制变色的
        rect = new Rect(middle, 0, getWidth(), getHeight());
        canvas.clipRect(rect);
        canvas.drawText(text, dx, baseLine, mOriginPaint);
        canvas.restore();
    }

    private void rightToLeft(Canvas canvas, String text, int middle) {
        canvas.save();
        //canvas.clipRect();//裁剪区域
        //绘制不变色的
        Rect rect = new Rect(getWidth() - middle, 0, getWidth(), getHeight());
        canvas.clipRect(rect);
        //获取自己的宽度
        Rect bounds = new Rect();
        mChangePaint.getTextBounds(text, 0, text.length(), bounds);
        int dx = getWidth() / 2 - bounds.width() / 2;
        Paint.FontMetricsInt fontMetrics = mChangePaint.getFontMetricsInt();
        int baseLine = getHeight() / 2 + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        canvas.drawText(text, dx, baseLine, mChangePaint);  //这么花其实还是只有一种颜色
        canvas.restore();

        canvas.save();
        //绘制变色的
        rect = new Rect(0, 0, getWidth() - middle, getHeight());
        canvas.clipRect(rect);
        canvas.drawText(text, dx, baseLine, mOriginPaint);
        canvas.restore();
    }

    /**
     * 设置方向
     */
    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    /**
     * 设置进度
     */
    public void setProgress(float progress) {
        this.mCurrentProgress = progress;
        invalidate();
    }

    /**
     * 设置改变的颜色
     */
    public void setChangeColor(int changeColor) {
        this.mChangePaint.setColor(changeColor);
    }

    /**
     * 设置改变的颜色
     */
    public void setOriginColor(int originColor) {
        this.mOriginPaint.setColor(originColor);
    }
}
