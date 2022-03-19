package com.v.module_widget.step_counter;

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
 * 仿qq运动步数
 */
public class QQStepView extends View {
    private int mOuterColor = Color.BLUE;
    private int mInnerColor = Color.RED;
    private int mBorderWidth = 20;
    private int mStepTextColor = Color.BLACK;
    private int mStepTextSize = 20;

    private Paint mOuterPaint;
    private Paint mInnerPaint;
    private Paint mTextPaint;
    private int mStepMax = 0; //总的步数
    private int mCurrentStep = 0; //当前步数

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //1、分析效果
        //2、确定自定义属性 编写attrs.xml
        //3、在布局中使用
        //4、在自定义view中获取自定属性
        //5、onMeasure()
        //6、画外圆弧，内圆弧，文字
        //7、其他处理

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mOuterColor = array.getColor(R.styleable.QQStepView_outerColor, mOuterColor);
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor, mInnerColor);
        mBorderWidth = (int) array.getDimension(R.styleable.QQStepView_borderWidth, mBorderWidth);
        mStepTextColor = array.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, mStepTextSize);
        array.recycle();

        mOuterPaint = new Paint();
        mOuterPaint.setColor(mOuterColor);
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setStrokeWidth(mBorderWidth);
        //Paint.Style.FILL 画笔实心
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);

        mInnerPaint = new Paint();
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        //Paint.Style.FILL 画笔实心  Paint.Style.STROKE空心
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);

        mTextPaint = new Paint();
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mStepTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //调用者再布局文件中可能是wrap_content   宽度高度不一致
        //获取模式 AT_MOST 40dp

        //宽度和高度不一致 取最小值 确保是一个正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //6.1、画外圆弧，分析：1.圆弧闭合 2.边缘没显示完整 3.开始位置是圆弧   解决：描边有宽度 mBorderWidth
//        int center = getWidth() / 2;
//        int radius = center - mBorderWidth/2;
//        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        RectF rectF = new RectF(mBorderWidth / 2f, mBorderWidth / 2f, getWidth() - mBorderWidth / 2f, getWidth() - mBorderWidth / 2f);
        canvas.drawArc(rectF, 135, 270, false, mOuterPaint);

        //6.2、内圆弧， 肯定不能写死  百分比 是使用这设置的从外面穿
        if (mStepMax == 0) return;
        float sweepAngle = (float) mCurrentStep / mStepMax;
        canvas.drawArc(rectF, 135, sweepAngle * 270, false, mInnerPaint);

        //6.3、文字
        String stepText = String.valueOf(mCurrentStep);
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);
        int dx = getWidth() / 2 - textBounds.width() / 2;
        //基线 baseLine
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(stepText, dx, baseLine, mTextPaint);
    }

    //7、其他  写几个方法动起来

    /**
     * 设置最大步数
     * <p>
     * synchronized 防止多线程调用
     */
    public synchronized void setStepMax(int max) {
        this.mStepMax = max;
    }

    /**
     * 设置当前步数
     */
    public synchronized void setCurrentStep(int currentStep) {
        this.mCurrentStep = currentStep;
        //不断绘制 onDraw()
        invalidate();
    }
}

