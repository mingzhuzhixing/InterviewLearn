package com.v.module_widget.shape_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 每秒切换一个图形
 */
public class ShapeView extends View {
    private Shape mCurrentShape = Shape.circle;
    private Paint mPaint;
    private Path mPath;

    //枚举形状
    public enum Shape {
        circle, square, triangle
    }

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mCurrentShape) {
            case circle:
                //画圆形
                int center = getWidth() / 2;
                mPaint.setColor(Color.YELLOW);
                canvas.drawCircle(center, center, center, mPaint);
                break;

            case square:
                //画正方形
                mPaint.setColor(Color.BLUE);
                //float left, float top, float right, float bottom, @NonNull Paint paint
                canvas.drawRect(0, 0, getWidth(), getWidth(), mPaint);
                break;

            case triangle:
                //画三角
                mPaint.setColor(Color.RED);
                //画路径
                if (mPath == null) {
                    mPath = new Path();
                    //等腰三角形
//                    mPath.moveTo(getWidth() / 2.0f, 0);
//                    mPath.lineTo(0, getHeight());
//                    mPath.lineTo(getWidth(), getHeight());
//                    //mPath.lineTo(getWidth() / 2.0f, 0);
//                    mPath.close();//关闭 把路径闭合

                    //等边三角形
//                    mPath.moveTo(getWidth() / 2.0f, 0);
//                    mPath.lineTo(0, (float) ((getWidth() / 2f) * Math.sqrt(3)));
//                    mPath.lineTo(getWidth(), (float) ((getWidth() / 2f) * Math.sqrt(3)));
//                    mPath.close();//关闭 把路径闭合

                    //等边三角形 居中
                    float dy = (float) ((getWidth() / 2f) * Math.sqrt(3));
                    float subDy = getWidth() - dy;
                    mPath.moveTo(getWidth() / 2.0f, subDy / 2.0f);
                    mPath.lineTo(subDy / 2.0f, dy + subDy / 2.0f);
                    mPath.lineTo(getWidth(), dy + subDy / 2.0f);
                    mPath.close();//关闭 把路径闭合
                }
                canvas.drawPath(mPath, mPaint);

                break;
        }
    }

    public void exchange() {
        switch (mCurrentShape) {
            case circle:
                //画圆形
                this.mCurrentShape = Shape.square;
                break;

            case square:
                //画正方形
                this.mCurrentShape = Shape.triangle;
                break;

            case triangle:
                //画三角
                this.mCurrentShape = Shape.circle;
                break;
        }
        invalidate();
    }
}
