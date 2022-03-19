package com.v.module_widget.watch_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.v.module_widget.R;

import java.util.Calendar;

public class WatchView extends View {
    //60个刻度
    private static final int pointNum = 60;
    //半径到View的高和宽的距离
    private int marginRadius = 20;
    //外部圆和刻度
    private Paint paint = null;
    //时间数字
    private Paint textPaint = null;
    //外圆圈指针
    private Paint inCirclePaint = null;
    //内圆圈指针
    private Paint outCirclePaint = null;
    //秒指针
    private Paint secondPaint = null;
    //屏幕的宽
    private int screenWidth = 0;
    //屏幕的高
    private int screenHeight = 0;
    //时钟的整体颜色
    private int color = Color.BLACK;
    //表半径的大小
    private int radius = 0;
    //表盘数字的大小
    private int numSize = 0;
    //内圆半径的大小
    private int inCircle = 0;
    //外圆半径的大小
    private int outCircle = 0;
    //数字距离半径的大小
    private int LongCalibration = 0;
    //View宽度的一半
    private int halfWidth = 0;
    //View高度的一半
    private int halfHeight = 0;
    //半径到长指针的间隔
    private int marginLongPoint = 0;
    //半径到短指针的间隔
    private int marginShortPoint = 0;
    //小时指针的间隔
    private int hourMargin = 0;
    //分钟指针的间隔
    private int minuteMargin = 0;
    //时间指针的宽的半径
    private static final int pointRadio = 6;
    //时间指针的圆弧半径
    private static final int circular = 100;
    //间隔
    private int marginText = 0;

    public WatchView(Context context) {
        this(context, null);
    }

    public WatchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WatchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Display display = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();

        float density = getResources().getDisplayMetrics().density;
        marginLongPoint = (int) density * 8;
        marginShortPoint = (int) density * 16;
        marginRadius = (int) density * 10;
        marginText = (int) density * 10;
        hourMargin = (int) density * 75;
        minuteMargin = (int) density * 40;

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ClockView, defStyleAttr, 0);
        int numCount = typedArray.getIndexCount();
        for (int i = 0; i < numCount; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.ClockView_numSize) {
                numSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue
                        .applyDimension(TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics()));
            } else if (attr == R.styleable.ClockView_color) {
                color = typedArray.getColor(attr, Color.BLACK);
            } else if (attr == R.styleable.ClockView_inCircle) {
                inCircle = typedArray.getInt(attr, 15);
            } else if (attr == R.styleable.ClockView_outCircle) {
                outCircle = typedArray.getInt(attr, 25);
            }
        }
        typedArray.recycle();
        initCanvas();
    }

    /**
     * 初始化画布
     */
    public void initCanvas() {
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(2, 2, 2, 2);

        textPaint = new Paint();
        textPaint.setColor(color);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(numSize);
        textPaint.setDither(true);
        textPaint.setSubpixelText(true);

        inCirclePaint = new Paint();
        inCirclePaint.setColor(color);
        inCirclePaint.setAntiAlias(true);
        inCirclePaint.setStrokeWidth(15);
        inCirclePaint.setAlpha(100);
        inCirclePaint.setDither(true);

        outCirclePaint = new Paint();
        outCirclePaint.setColor(color);
        outCirclePaint.setAntiAlias(true);
        outCirclePaint.setStrokeWidth(15);
        outCirclePaint.setDither(true);

        secondPaint = new Paint();
        secondPaint.setColor(color);
        secondPaint.setAntiAlias(true);
        secondPaint.setStrokeWidth(5);
        secondPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽度
        int width = MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY ? MeasureSpec.getSize(widthMeasureSpec) : screenWidth;
        //获取高度
        int height = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY ? MeasureSpec.getSize(heightMeasureSpec) : screenHeight / 3;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //得到圆的半径
        if (getWidth() > getHeight()) {
            radius = getHeight() / 2 - marginRadius;
        } else {
            radius = getWidth() / 2 - marginRadius;
        }
        //获得View一半的宽度和高度
        halfWidth = getWidth() / 2;
        halfHeight = getHeight() / 2;
        //保存状态
        canvas.save();
        //画大圆
        canvas.drawCircle(halfWidth, halfHeight, radius, paint);
        //画中间大圆
        canvas.drawCircle(halfWidth, halfHeight, outCircle, inCirclePaint);
        //画中间小圆
        canvas.drawCircle(halfWidth, halfHeight, inCircle, outCirclePaint);
        //画60个刻度和时钟数字
        drawClockScale(canvas);
        //绘制时间指针
        refreshTime(canvas);
        //返回状态
        canvas.restore();
        //每隔一秒刷新
        postInvalidateDelayed(1000);
    }

    //画60个刻度
    public void drawClockScale(Canvas canvas) {
        canvas.translate(halfWidth, halfHeight);
        canvas.save();
        //长指针的长
        LongCalibration = radius / marginLongPoint;
        //短指针的长
        int ShortCalibration = radius / marginShortPoint;
        for (int i = 0; i < pointNum; i++) {
            if (i % 5 == 0) {
                //绘画文字
                canvas.save();
                Rect rect = new Rect();
                int number = i == 0 ? 12 : (i / 5);
                textPaint.getTextBounds((number + ""), 0, (number + "").length(), rect);
                canvas.translate(0, -radius + LongCalibration + ((rect.bottom - rect.top) / 2) + marginText);
                canvas.rotate(-6 * i);
                canvas.drawText(number + "", 0, (rect.bottom - rect.top) / 2, textPaint);
                canvas.restore();
                //画线
                canvas.drawLine(0, -radius + LongCalibration, 0, -radius, paint);
            } else {
                canvas.drawLine(0, -radius + ShortCalibration, 0, -radius, paint);
            }
            canvas.rotate(6);
        }
        canvas.restore();
    }

    //时间指针
    public void drawCircleLine(Canvas canvas, int hour, int minute, int second) {
        //长指针到圆圈的间隙
        int marginLong = radius - LongCalibration - minuteMargin;
        //短指针到圆圈的间隙
        int marginShort = radius - LongCalibration - hourMargin;
        canvas.rotate(180);

        //画小时指针
        RectF hourRectF = new RectF(-pointRadio, -pointRadio, pointRadio, marginShort);
        canvas.save();
        canvas.rotate(hour);
        canvas.drawRoundRect(hourRectF, circular, circular, outCirclePaint);
        canvas.restore();

        //画分钟指针
        RectF minuteRectF = new RectF(-pointRadio, -pointRadio, pointRadio, marginLong);
        canvas.save();
        canvas.rotate(minute);
        canvas.drawRoundRect(minuteRectF, circular, circular, outCirclePaint);
        canvas.restore();

        //画秒指针
        canvas.save();
        canvas.rotate(second);
        canvas.drawLine(0, 0, 0, radius - 10, secondPaint);
        canvas.restore();
    }

    //获取时间指针对应的角度
    public void refreshTime(Canvas canvas) {
        Calendar mCalendar = Calendar.getInstance();
        int tempHour = mCalendar.get(Calendar.HOUR);
        int tempMinute = mCalendar.get(Calendar.MINUTE);
        int tempSecond = mCalendar.get(Calendar.SECOND);
        int hourRotate = Float.valueOf(360 * ((float) tempHour / 12)).intValue();
        //计算出份指针的旋转的角度
        int minuteRotate = Float.valueOf(360 * ((float) tempMinute / 60)).intValue();
        //计算出时指针旋转的角度，注(时的角度是当前小时的角度再加分钟所引起小时偏转的角度)
        hourRotate += Float.valueOf(30 * ((float) minuteRotate / 360)).intValue();
        //计算出秒指针旋转的角度
        int secondRotate = Float.valueOf(360 * ((float) tempSecond / 60)).intValue();
        drawCircleLine(canvas, hourRotate, minuteRotate, secondRotate);
    }
}
