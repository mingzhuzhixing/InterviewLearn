package com.v.module_widget.curve_circle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.v.module_widget.R
import java.lang.Math.min

class WaveProgressView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    // 定义画笔
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val wavePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 定义圆心坐标和半径
    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f

    // 定义波浪线属性
    private var waveCount = 2 // 波浪线个数
    private var waveInterval = 0 // 波浪线间隔
    private var waveHeight = 0 // 波浪线高度
    private var waveAmplitude = 0 // 波浪线振幅
    private var waveSpeed = 0 // 波浪线速度
    private var waveDirection = -1 // 波浪线方向：向上为-1，向下为1

    // 定义进度属性
    private var progress = 0 // 当前进度
    private var maxProgress = 100 // 最大进度

    init {
        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.WaveProgressView)

        // 设置背景颜色
        val bgColor = typedArray?.getColor(R.styleable.WaveProgressView_backgroundColor, Color.WHITE)
        setBackgroundColor(bgColor ?: Color.WHITE)

        // 初始化圆画笔属性
        val circleColor = typedArray?.getColor(R.styleable.WaveProgressView_circleColor, Color.BLUE)
        val circleWidth = typedArray?.getDimension(R.styleable.WaveProgressView_circleWidth, 10f)
                ?: 10f
        circlePaint.color = circleColor ?: Color.BLUE
        circlePaint.strokeWidth = circleWidth
        circlePaint.style = Paint.Style.STROKE

        // 初始化波浪线画笔属性
        val waveColor = typedArray?.getColor(R.styleable.WaveProgressView_waveColor, Color.BLUE)
        waveHeight = typedArray?.getDimensionPixelSize(R.styleable.WaveProgressView_waveHeight, 40)
                ?: 40
        waveAmplitude = typedArray?.getDimensionPixelSize(R.styleable.WaveProgressView_waveAmplitude, 20) ?: 20
        waveSpeed = typedArray?.getInt(R.styleable.WaveProgressView_waveSpeed, 10) ?: 10
        waveDirection = typedArray?.getInt(R.styleable.WaveProgressView_waveDirection, -1) ?: -1
        waveInterval = typedArray?.getInt(R.styleable.WaveProgressView_waveInterval, 40)
                ?: 40
        wavePaint.color = waveColor ?: Color.BLUE

        // 释放资源
        typedArray?.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制圆
        canvas.drawCircle(centerX, centerY, radius, circlePaint)

        // 绘制波浪线
        val waveX = (progress * waveSpeed) % width
        val startY = centerY + (radius * (1 - 2 * progress.toFloat() / maxProgress))

        // 计算波浪线路径
        val path = Path()
        val waveWidth = width / waveCount // 波长，即一个波浪的宽度
        path.moveTo((-waveWidth + waveX).toFloat(), startY)
        for (i in 0 until waveCount + 1) {
            val x1 = i * waveWidth + (waveX % waveWidth)
            val y1 = startY + waveDirection * waveAmplitude
            val x2 = x1 + waveWidth / 2
            val y2 = startY
            val x3 = x1 + waveWidth
            val y3 = startY - waveDirection * waveAmplitude
            path.rQuadTo(waveWidth / 4f, waveDirection.toFloat() * waveHeight, waveWidth / 2f, 0f)
            path.rQuadTo(waveWidth / 4f, waveDirection.toFloat() * -waveHeight, waveWidth / 2f, 0f)
        }
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(0f, height.toFloat())
        path.close()
        canvas.drawPath(path, wavePaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        radius = min(centerX, centerY) - waveHeight
    }

    /**
     * 设置进度值
     * @param progress 进度值（0~maxProgress）
     */
    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }

    /**
     * 设置最大进度值
     * @param maxProgress 最大进度值
     */
    fun setMaxProgress(maxProgress: Int) {
        this.maxProgress = maxProgress
    }
}