package com.v.module_widget.curve_circle;

import static java.lang.Math.min;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.v.module_widget.R;

public class WaveProgressView2 extends View {
    // 定义画笔
    private Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint wavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // 定义圆心坐标和半径
    private float centerX = 0f;
    private float centerY = 0f;
    private float radius = 0f;

    // 定义波浪线属性
    private int waveCount = 2;// 波浪线个数
    private int waveInterval = 0;// 波浪线间隔
    private int waveHeight = 0;// 波浪线高度
    private int waveAmplitude = 0; // 波浪线振幅
    private int waveSpeed = 0;// 波浪线速度
    private int waveDirection = -1; // 波浪线方向：向上为-1，向下为1

    // 定义进度属性
    private int progress = 0; // 当前进度
    private int maxProgress = 100; // 最大进度

    public WaveProgressView2(Context context) {
        this(context, null);
    }

    public WaveProgressView2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveProgressView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaveProgressView);
        // 设置背景颜色
        int bgColor = typedArray.getColor(R.styleable.WaveProgressView_backgroundColor, Color.WHITE);
        setBackgroundColor(bgColor);

        // 初始化圆画笔属性
        int circleColor = typedArray.getColor(R.styleable.WaveProgressView_circleColor, Color.BLUE);
        float circleWidth = typedArray.getDimension(R.styleable.WaveProgressView_circleWidth, 10f);
        circlePaint.setColor(circleColor);
        circlePaint.setStrokeWidth(circleWidth);
        circlePaint.setStyle(Paint.Style.STROKE);

        // 初始化波浪线画笔属性
        int waveColor = typedArray.getColor(R.styleable.WaveProgressView_waveColor, Color.BLUE);
        waveHeight = typedArray.getDimensionPixelSize(R.styleable.WaveProgressView_waveHeight, 40);
        waveAmplitude = typedArray.getDimensionPixelSize(R.styleable.WaveProgressView_waveAmplitude, 20);
        waveSpeed = typedArray.getInt(R.styleable.WaveProgressView_waveSpeed, 10);
        waveDirection = typedArray.getInt(R.styleable.WaveProgressView_waveDirection, -1);
        waveInterval = typedArray.getInt(R.styleable.WaveProgressView_waveInterval, 40);
        wavePaint.setColor(waveColor);
        // 释放资源
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆
        canvas.drawCircle(centerX, centerY, radius, circlePaint);

        // 绘制波浪线
        float waveX = (progress * waveSpeed) % getWidth();
        float startY = centerY + (radius * (1 - 2.0f * progress / maxProgress));

        // 计算波浪线路径
        Path path = new Path();
        int waveWidth = getWidth() / waveCount; // 波长，即一个波浪的宽度
        path.moveTo(-waveWidth + waveX, startY);
        for (int i = 0; i < waveCount; i++) {
            path.rQuadTo(waveWidth / 4f, ((float) waveDirection) * waveHeight, waveWidth / 2f, 0f);
            path.rQuadTo(waveWidth / 4f, ((float) waveDirection) * -waveHeight, waveWidth / 2f, 0f);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0f, ((float) getHeight()));
        path.close();
        canvas.drawPath(path, wavePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2f;
        centerY = h / 2f;
        radius = min(centerX, centerY) - waveHeight;
    }

    /**
     * 设置进度值
     *
     * @param progress 进度值（0~maxProgress）
     */
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    /**
     * 设置最大进度值
     *
     * @param maxProgress 最大进度值
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }
}
