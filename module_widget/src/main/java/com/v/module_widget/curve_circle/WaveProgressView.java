package com.v.module_widget.curve_circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class WaveProgressView extends View {
    private Paint mBackgroundPaint;  //圆形背景画笔
    private Paint mWavePaint;        //波浪线画笔
    private Path mWavePath;          //波浪线路径
    private float mWaveAmplitude;    //波浪线振幅
    private float mWaveFrequency;    //波浪线频率
    private float mWavePhase;        //波浪线相位
    private float mProgress;         //波浪线填充比例
    private int mGradientStartColor; //渐变起始颜色
    private int mGradientEndColor;   //渐变结束颜色

    public WaveProgressView(Context context) {
        super(context);
        init();
    }

    public WaveProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化圆形背景画笔
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.parseColor("#EDEDED"));
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        //初始化波浪线画笔
        mWavePaint = new Paint();
        mWavePaint.setStrokeWidth(2);
        mWavePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mWavePaint.setAntiAlias(true);
        mWavePath = new Path();

        //设置默认参数
        mWaveAmplitude = 10;
        mWaveFrequency = 0.01f;
        mProgress = 0f;
        mGradientStartColor = Color.parseColor("#FF7F50");
        mGradientEndColor = Color.parseColor("#008B8B");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        //绘制圆形背景
        canvas.drawCircle(width / 2f, height / 2f, width / 2f, mBackgroundPaint);

        //绘制波浪线
        float centerY = height * (1 - mProgress);
        mWavePath.reset();
        mWavePath.moveTo(0, centerY);
        for (float x = 0; x <= width; x += 20) {
            float y = (float) (mWaveAmplitude * Math.sin(mWaveFrequency * x + mWavePhase) + centerY);
            mWavePath.lineTo(x, y);
        }
        mWavePath.lineTo(width, height);
        mWavePath.lineTo(0, height);
        mWavePath.close();

        //应用渐变处理器
        Shader shader = new LinearGradient(0, centerY, 0, height, mGradientStartColor, mGradientEndColor, Shader.TileMode.CLAMP);
        mWavePaint.setShader(shader);

        canvas.drawPath(mWavePath, mWavePaint);

//        canvas.cl
    }

    public void setProgress(float progress) {
        mProgress = progress;
        mWavePhase = (float) (mProgress * 2 * Math.PI);
        invalidate();
    }

    public void setWaveAmplitude(int amplitude) {
        mWaveAmplitude = amplitude;
        invalidate();
    }

    public void setGradientStartColor(int color) {
        mGradientStartColor = color;
        invalidate();
    }

    public void setGradientEndColor(int color) {
        mGradientEndColor = color;
        invalidate();
    }
}