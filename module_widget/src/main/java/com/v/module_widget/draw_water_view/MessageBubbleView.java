package com.v.module_widget.draw_water_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;

import com.v.module_utils.DensityUtils;

/**
 * 消息气泡view
 */
public class MessageBubbleView extends View {
    //两个圆
    private PointF mFixationPoint, mDragPoint;
    //拖拽圆的半径
    private int mDragRadius = 10;
    //固定圆的半径 最大的初始半径
    private int mFixationRadiusMax = 8;
    private int mFixationRadiusMin = 2;
    private int mFixationRadius;
    private Paint mPaint;

    private Bitmap mDragBitmap;

    public MessageBubbleView(Context context) {
        this(context, null);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageBubbleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragRadius = DensityUtils.dip2px(context, mDragRadius);
        mFixationRadiusMax = DensityUtils.dip2px(context, mFixationRadiusMax);
        mFixationRadiusMin = DensityUtils.dip2px(context, mFixationRadiusMin);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画两个圆
        if (mDragPoint == null || mFixationPoint == null) {
            return;
        }
        //拖拽圆
        canvas.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius, mPaint);

        //画固定圆  有一个初始的大小，而且他的半径是随着距离的增大而减少，小到一定程度就不画了

        Path bezierPath = getBezierPath();
        if (bezierPath != null) {
            canvas.drawCircle(mFixationPoint.x, mFixationPoint.y, mFixationRadius, mPaint);

            //画贝塞尔曲线
            canvas.drawPath(bezierPath, mPaint);
        }

        //画图片 位置也是手指移动的位置，中心位置才是手指拖动的位置
        if (mDragBitmap != null) {
            canvas.drawBitmap(mDragBitmap, mDragPoint.x - mDragBitmap.getWidth() / 2.0f, mDragPoint.y - mDragBitmap.getHeight() / 2.0f, null);
        }
    }

    private Path getBezierPath() {
        //计算两个点的距离
        double distance = getDistance(mDragPoint, mFixationPoint);
        mFixationRadius = (int) (mFixationRadiusMax - distance / 14);
        if (mFixationRadius < mFixationRadiusMin) {
            //超过一定距离,就不用去画固定圆了
            return null;
        }
        Path bezierPath = new Path();
        float dy = mDragPoint.y - mFixationPoint.y;
        float dx = mDragPoint.x - mFixationPoint.x;
        if (dx == 0) {
            dx = 0.001f;
        }
        //求斜率
        float tanA = dy / dx;
        //求角a
        double arcTanA = Math.atan(tanA);
        //p0
        float p0x = (float) (mFixationPoint.x + mFixationRadius * Math.sin(arcTanA));
        float p0y = (float) (mFixationPoint.y - mFixationRadius * Math.cos(arcTanA));

        //p1
        float p1x = (float) (mDragPoint.x + mDragRadius * Math.sin(arcTanA));
        float p1y = (float) (mDragPoint.y - mDragRadius * Math.cos(arcTanA));

        //p2
        float p2x = (float) (mDragPoint.x - mDragRadius * Math.sin(arcTanA));
        float p2y = (float) (mDragPoint.y + mDragRadius * Math.cos(arcTanA));

        //p3
        float p3x = (float) (mFixationPoint.x - mFixationRadius * Math.sin(arcTanA));
        float p3y = (float) (mFixationPoint.y + mFixationRadius * Math.cos(arcTanA));

        //拼装 贝塞尔曲线
        bezierPath.moveTo(p0x, p0y); //移动到p0x,p0y点
        //两个点  第一个点（控制点）
        PointF controlPoint = getControlPoint();
        //画了第一条线贝塞尔曲线   控制点 结束点画曲线
        bezierPath.quadTo(controlPoint.x, controlPoint.y, p1x, p1y);
        //连接点
        bezierPath.lineTo(p2x, p2y);  //连接到
        //画了第二条线贝塞尔曲线  控制点 结束点画曲线
        bezierPath.quadTo(controlPoint.x, controlPoint.y, p3x, p3y);
        bezierPath.close();
        return bezierPath;
    }

    /**
     * 控制点
     */
    private PointF getControlPoint() {
        float dx = mDragPoint.x + mFixationPoint.x;
        float dy = mDragPoint.y + mFixationPoint.y;
        return new PointF(dx / 2, dy / 2);
    }

    /**
     * 勾股定理计算两个点的距离
     */
    private double getDistance(PointF point1, PointF point2) {
        float distanceX = point1.x - point2.x;
        float distanceY = point1.y - point2.y;
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                //手指按下的指定当前的位置
//                float downX = event.getX();
//                float downY = event.getY();
//                initPoint(downX, downY);
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                float moveX = event.getX();
//                float moveY = event.getY();
//                updateDragPoint(moveX, moveY);
//                break;
//
//            case MotionEvent.ACTION_UP:
//
//                break;
//        }
//        invalidate();
//        return true;
//    }

    /**
     * 初始化位置
     */
    public void initPoint(float downX, float downY) {
        mFixationPoint = new PointF(downX, downY);
        mDragPoint = new PointF(downX, downY);
    }

    /**
     * 更新当前拖拽点的位置
     */
    public void updateDragPoint(float moveX, float moveY) {
        mDragPoint.x = moveX;
        mDragPoint.y = moveY;

        //重新绘制
        invalidate();
    }

    /**
     * 绑定可以拖拽的控件
     *
     * @param view
     * @param listener
     */
    public static void attach(View view, BubbleMessageTouchListener.BubbleDisappearListener listener) {
        if (view == null) {
            return;
        }
        view.setOnTouchListener(new BubbleMessageTouchListener(view.getContext(), view, listener));
    }

    public void setDragBitmap(Bitmap bitmap) {
        this.mDragBitmap = bitmap;
    }

    /**
     * 处理手指抬起
     */
    public void handleActionUp() {
        if (mFixationRadius < mFixationRadiusMax) {
            //回弹 ValueAnimator 之变化的动画 0变化到1
            ValueAnimator animator = ObjectAnimator.ofFloat(1);
            animator.setDuration(350);
            PointF start = new PointF(mDragPoint.x, mDragPoint.y);
            PointF end = new PointF(mFixationPoint.x, mFixationPoint.y);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float percent = (float) animation.getAnimatedValue();
                    PointF pointF = BubbleUtils.getPointByPercent(start, end, percent);
                    //用代码更新拖拽点
                    updateDragPoint(pointF.x, pointF.y);
                }
            });
            //设置一个差值器 在结束的时候回弹
            animator.setInterpolator(new OvershootInterpolator(3.0f));

            //还要通知 TouchListener 移除当前的view，然后显示静态的view
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (mBubbleListener != null) {
                        mBubbleListener.restore();
                    }
                }
            });
            animator.start();
        } else {
            //爆炸 消失
            if (mBubbleListener != null) {
                mBubbleListener.dismiss(mDragPoint);
            }
        }
    }

    private MessageBubbleListener mBubbleListener;

    public void setMessageBubbleListener(MessageBubbleListener listener) {
        this.mBubbleListener = listener;
    }

    public interface MessageBubbleListener {
        //还原
        void restore();

        //消失爆炸
        void dismiss(PointF pointF);
    }
}
