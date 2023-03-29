package com.v.module_widget.curve_circle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

public class CurveCircleLayout extends View {
    private int width;
    private int height;
    private Bitmap mBitmap;
    private Canvas canvasBit;
    private Paint mPaintCircle;
    private Paint mPaintline;
    private int count;
    private Path path;
    private int progress;
    private int Maxprogress;

    public void setMaxprogress(int maxprogress) {
        Maxprogress = maxprogress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;//贝塞尔曲线周期循环
        invalidate();
    }

    public CurveCircleLayout(Context context) {
        super(context, null);
    }

    public CurveCircleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurveCircleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaintCircle = new Paint();
        mPaintCircle.setColor(Color.BLUE);
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.FILL);

        path = new Path();

        mPaintline = new Paint();
        mPaintline.setAntiAlias(true);
        mPaintline.setStyle(Paint.Style.FILL);
        mPaintline.setColor(Color.YELLOW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvasBit = new Canvas(mBitmap);//新建bitmap的canvas
        mPaintline.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));//显示圆和交叠的部分
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);//背景色
        canvasBit.drawCircle(300, 300, 150, mPaintCircle);
        path.reset();
        path.moveTo(500, progress);//矩形右边框到圆底部
        path.lineTo(500, 500);//右边框
        path.lineTo(count, 500);//下边框
        path.lineTo(count, progress);//左边框
        for (int i = 0; i < 10; i++) {//循环形成10个周期的波浪封矩形上边框
            path.rQuadTo(20, 5, 50, 0);
            path.rQuadTo(20, -5, 50, 0);
        }
        canvasBit.drawPath(path, mPaintline);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }
}
