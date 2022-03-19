package com.v.module_widget.progess_bar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.v.module_widget.R;

/**
 * 自定义圆形进度条
 */
public class ProgressBarView extends View {
    private Paint mOuterColorPaint;
    private Paint mInnerColorPaint;
    private Paint mTextPaint;

    private int mBorderWidth;
    private int mCurrentProgress = 0;
    private int mMaxProgress;

    public ProgressBarView(Context context) {
        this(context, null);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        int outerColor = array.getColor(R.styleable.QQStepView_outerColor, Color.BLUE);
        int innerColor = array.getColor(R.styleable.QQStepView_innerColor, Color.RED);
        mBorderWidth = (int) array.getDimension(R.styleable.QQStepView_borderWidth, 20);
        int textColor = array.getColor(R.styleable.QQStepView_stepTextColor, Color.RED);
        int textSize = (int) array.getDimension(R.styleable.QQStepView_stepTextSize, 15);
        array.recycle();

        mOuterColorPaint = getPaint(outerColor);
        mOuterColorPaint.setStrokeWidth(mBorderWidth);
        mOuterColorPaint.setStyle(Paint.Style.STROKE);

        mInnerColorPaint = getPaint(innerColor);
        mInnerColorPaint.setStrokeWidth(mBorderWidth);
        mInnerColorPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = getPaint(textColor);
        mTextPaint.setTextSize(textSize);
    }

    /**
     * 获取画笔
     */
    private Paint getPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(Math.min(measureWidth, measureHeight), Math.min(measureWidth, measureHeight));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvasCircle1(canvas);
        //第二种画圆方式
        canvasCircle2(canvas);

        canvasText(canvas);
    }

    /**
     * 第一种画圆方式
     */
    private void canvasCircle1(Canvas canvas) {
        //画外圆
        float halfBorderWidth = mBorderWidth / 2.0f;
        float dy = getWidth() - halfBorderWidth;
        RectF rectF1 = new RectF(halfBorderWidth, halfBorderWidth, dy, dy);
        canvas.drawArc(rectF1, 0, 360, false, mOuterColorPaint);

        //画内圆
        float percent = mCurrentProgress / (float) mMaxProgress;
        //float left, float top, float right, float bottom
        RectF rectF = new RectF(halfBorderWidth, halfBorderWidth, dy, dy);
        //(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint
        canvas.drawArc(rectF, 0, percent * 360, false, mInnerColorPaint);
    }

    /**
     * 第二种画圆方式
     */
    private void canvasCircle2(Canvas canvas) {
        int center = getWidth() / 2;
        //画外圆float cx, float cy, float radius, @NonNull Paint paint
        canvas.drawCircle(center, center, center - mBorderWidth / 2.0f, mOuterColorPaint);

        //画内圆
        float halfBorderWidth = mBorderWidth / 2.0f;
        float dy = getWidth() - halfBorderWidth;
        float percent = mCurrentProgress / (float) mMaxProgress;
        //float left, float top, float right, float bottom
        RectF rectF = new RectF(halfBorderWidth, halfBorderWidth, dy, dy);
        //(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint
        canvas.drawArc(rectF, 0, percent * 360, false, mInnerColorPaint);
    }

    /**
     * 画文字
     */
    private void canvasText(Canvas canvas) {
        //画文字 @NonNull String text, float x, float y, @NonNull Paint paint
        //计算文字基线
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        float dy_text = (fontMetrics.bottom - fontMetrics.top) / 2f - fontMetrics.bottom;
        float baseLine = getWidth() / 2.0f + dy_text;

        //计算文字的位置
        String text = mCurrentProgress + "%";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), rect);
        float dx = getWidth() / 2.0f - rect.width() / 2.0f;

        canvas.drawText(text, dx, baseLine, mTextPaint);
    }


    public void setMaxProgress(int maxProgress) {
        this.mMaxProgress = maxProgress;
    }

    public void setCurrentProgress(int progress) {
        this.mCurrentProgress = progress;
        invalidate();
    }

}
