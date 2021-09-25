package com.youshu.widget_module;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WatchView extends View {
    private int mWidth;
    private int mHeight;
    private int mRadius;//圆盘的半径
    private int width;//宽度
    private Paint mPaint;//画笔


    public WatchView(Context context) {
        super(context);
        init();
    }

    public WatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WatchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        if (mWidth > mHeight) {
            width = mHeight;
        } else {
            width = mWidth;
        }

        mRadius = width / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画刻度线
//        mPaint.setStrokeWidth(3);
//        mPaint.setAntiAlias(true);
//        for (int i = 0; i < 12; i++) {
//            if (i == 0 || i == 3 || i == 6 || i == 9) {
//                mPaint.setStrokeWidth(5);
//                mPaint.setTextSize(80);
//                canvas.drawLine(width / 2, mHeight / 2 - mWidth / 2,
//                        mWidth / 2, mHeight / 2 - mWidth / 2 + 60,
//                        mPaint);
//                String degree = String.valueOf(i);
//                canvas.drawText(degree, mWidth / 2 - mPaint.measureText(degree) / 2
//                        , mHeight / 2 - mWidth / 2 + 120, mPaint);
//            } else {
//                mPaint.setStrokeWidth(5);
//                mPaint.setTextSize(30);
//
//                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
//                        mWidth / 2, mHeight / 2 - mWidth / 2 + 30,
//                        mPaint);
//
//                String degree = String.valueOf(i);
//                canvas.drawText(degree, mWidth / 2 - mPaint.measureText(degree) / 2
//                        , mHeight / 2 - mWidth / 2 + 60, mPaint);
//            }
//            canvas.rotate(30, mWidth / 2, mHeight / 2);
//        }
//
//        //画指针
//        canvas.translate(mWidth / 2, mHeight / 2);
//
//        mPaint.setStrokeWidth(20);
//        canvas.drawLine(0, 0, 100, 100, mPaint);
//
//        mPaint.setStrokeWidth(10);
//        canvas.drawLine(0, 0, 100, 200, mPaint);

        for (int i = 0; i < 60; i++) {

        }


        //画圆盘
        canvas.drawCircle(width / 2, width / 2, mRadius, mPaint);
    }
}
