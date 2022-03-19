package com.v.module_widget.text_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.v.module_widget.R;

@SuppressLint({"Recycle", "DrawAllocation"})
public class CustomTextView extends View {
    private String mText;
    private int mTextSize = 15;
    private int mTextColor = Color.BLACK;

    private Paint mPaint;

    /**
     * 构造函数在代码中new的使用调用
     * TextView tv=new TextView(this);
     */
    public CustomTextView(Context context) {
        this(context, null);
    }

    /**
     * 在布局layout中使用
     * <com.v.module_widget.view.TextView
     * android:layout_width="wrap_content"
     * android:layout_height="wrap_content"
     * android:text="Hello world"/>
     */
    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 在布局layout中使用(调用)，但是会有style
     * <com.v.module_widget.view.TextView
     * style="@style/default"
     * android:text="Hello world"/>
     */
    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义的属性  (系统有的自定义属性，我们是不能重新定义的)

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextView);

        mText = array.getString(R.styleable.TextView_cusText);
        mTextColor = array.getColor(R.styleable.TextView_cusTextColor, mTextColor);
        //15 :15px or 15sp
        mTextSize = array.getDimensionPixelSize(R.styleable.TextView_cusTextSize, sp2px(mTextSize));

        //回收
        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setTextSize(mTextSize);//设置字体大小
        mPaint.setColor(mTextColor);//设置字体颜色
    }

    /**
     * 测量方法
     * <p>
     * MeasureSpec.AT_MOST://在布局中指定的warp_content
     * MeasureSpec.EXACTLY://在布局中指定的确切的  100dp  match_parent fill_parent
     * MeasureSpec.UNSPECIFIED://尽可能的大,很好能用到，ListView ScrollView 在测量子布局的时候会用UNSPECIFIED，会在自定义ViewGroup的时候详细讲
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //布局的宽高都是由着方法指定
        //指定空间的宽高，需要测量

        //widthMeasureSpec heightMeasureSpec:是一个32为值  前面2位是模式，后面30位是宽高值

        //获取宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //获取宽高的值
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //1、确定的值，这时候不需要计算，给的多少就是多少


        //2、给的是wrap_content,需要计算
        // 通过画笔获取字体的宽高
        Rect bounds = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), bounds);
        if (widthMode == MeasureSpec.AT_MOST) {
            //计算的宽度 与 字体的长度有关  与字体的大小  用画笔来测量
            widthSize = bounds.width() + getPaddingLeft() + getPaddingRight();
        }

        if (heightMode == MeasureSpec.AT_MOST) {
            heightSize = bounds.height() + getPaddingTop() + getPaddingBottom();
        }

        //设置控件的宽高
        setMeasuredDimension(widthSize, heightSize);
    }

    /**
     * 用于绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画文本
        //String text:文字,
        // float x:开始的位置,
        // float y:基线 baseLine,  getHeight() / 2：中心高度知道
        // Paint paint 画笔

        //dy 代表的是：高度的一个到baseLine的距离
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        //top 是一个负值  bottom 是一个正直  top bottom的代表是baseLine到文字顶部或底部的距离
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;

        int x = getPaddingLeft();
        canvas.drawText(mText, x, baseLine, mPaint);
        //画弧
        //canvas.drawArc();
        //画园
        //canvas.drawCircle();
    }

    /**
     * 处理用户交互
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                //手指抬起
                break;

            case MotionEvent.ACTION_MOVE:
                //手指移动
                break;

            case MotionEvent.ACTION_DOWN:
                //手指按下
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 自定义属性
     * 自定义属性是用来配置的   android:text="Hello world"是系统的一个自定义属性
     * <p>
     * 1、在res下面新建atts.xml
     */

    /**
     * sp转化成px
     */
    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
