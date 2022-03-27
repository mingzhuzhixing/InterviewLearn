package com.v.module_widget.draw_water_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.v.module_rxjava.bean.ProjectItem;
import com.v.module_widget.R;

/**
 * 监听当前view 的触摸时间
 * 分析问题  解决问题
 * 1、位置不对 event.getX() 相对于父布局位置view (该view)   应该要获取屏幕的位置 event.getRawX()
 * 2、Y位置不对,其实相当于一个状态栏的高度
 * 3、我拖拽的应该是一个view?获取那个没有动的view的bitmap，然后再去画
 * 4、拖动要回去，如果贝塞尔曲线没消失那么就回弹，
 * 动画结束的位置在我们的手指按下的位置，但是当view被设置显示的时候是在原来的位置（中心点）
 * 5、如果贝塞尔曲线消失就爆炸
 */
public class BubbleMessageTouchListener implements View.OnTouchListener, MessageBubbleView.MessageBubbleListener {
    //原来需要拖动爆炸的view
    private View mStaticView;
    private WindowManager mWindowManager;
    private MessageBubbleView mMessageBubbleView;
    private WindowManager.LayoutParams mParams;
    private Context mContext;
    //爆炸的动画
    private FrameLayout mBombFrame;
    private ImageView mBombImage;

    private BubbleDisappearListener mDisappearListener;

    public BubbleMessageTouchListener(Context context, View view, BubbleDisappearListener listener) {
        this.mStaticView = view;
        this.mContext = context;
        this.mDisappearListener = listener;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mMessageBubbleView = new MessageBubbleView(context);
        mMessageBubbleView.setMessageBubbleListener(this);
        mParams = new WindowManager.LayoutParams();
        //背景要透明
        mParams.format = PixelFormat.TRANSPARENT;

        mBombFrame = new FrameLayout(context);
        mBombImage = new ImageView(context);
        mBombImage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mBombFrame.addView(mBombImage);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //首先把自己隐藏
                mStaticView.setVisibility(View.GONE);
                //要在windowManager上面搞一个view,上一节写好的贝塞尔的view
                mWindowManager.addView(mMessageBubbleView, mParams);
                //初始化贝塞尔view 的点

                //保证固定圆的中心在view的中心
                int[] location = new int[2];
                mStaticView.getLocationOnScreen(location);
                //mMessageBubbleView.initPoint(event.getRawX(), event.getRawY() - BubbleUtils.getStatusBarHeight(mContext));
                mMessageBubbleView.initPoint(location[0] + mStaticView.getWidth() / 2.0f, location[1] + mStaticView.getWidth() / 2.0f - BubbleUtils.getStatusBarHeight(mContext));

                //给消息拖拽设置一个bitmap
                mMessageBubbleView.setDragBitmap(getBitmap(mStaticView));

                break;

            case MotionEvent.ACTION_MOVE:
                mMessageBubbleView.updateDragPoint(event.getRawX(), event.getRawY() - BubbleUtils.getStatusBarHeight(mContext));
                break;

            case MotionEvent.ACTION_UP:
                mMessageBubbleView.handleActionUp();
                break;
        }
        return true;
    }

    /**
     * 从一个view中获取一个bitmap
     */
    private Bitmap getBitmap(View staticView) {
        staticView.buildDrawingCache();
        Bitmap bitmap = staticView.getDrawingCache();
        return bitmap;
    }

    @Override
    public void restore() {
        //把消息的view移除
        mWindowManager.removeView(mMessageBubbleView);
        //把原来的view显示
        mStaticView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss(PointF pointF) {
        //要去执行爆炸动画（帧动画）
        //原来的view的view肯定要移除
        mWindowManager.removeView(mMessageBubbleView);

        //要在mWindowManager添加一个爆炸动画
        mWindowManager.addView(mBombFrame, mParams);
        mBombImage.setBackgroundResource(R.drawable.anim_bubble_pop);

        AnimationDrawable drawable = (AnimationDrawable) mBombImage.getBackground();

        mBombImage.setX(pointF.x - drawable.getIntrinsicWidth() / 2.0f);
        mBombImage.setY(pointF.y - drawable.getIntrinsicHeight() / 2.0f);

        drawable.start();
        //等他执行之后我要移除掉这个  爆炸动画 也就是mBombFrame
        mBombImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWindowManager.removeView(mBombFrame);
                //通知一下外面该消失
                if (mDisappearListener != null) {
                    mDisappearListener.dismiss(mStaticView);
                }
            }
        }, getAnimationDrawableTime(drawable));
    }

    /**
     * 获取帧动画 总时间
     */
    private long getAnimationDrawableTime(AnimationDrawable drawable) {
        int numberOfFrames = drawable.getNumberOfFrames();
        long time = 0;
        for (int i = 0; i < numberOfFrames; i++) {
            time += drawable.getDuration(i);
        }
        return time;
    }

    /**
     * 气泡消失
     */
    public interface BubbleDisappearListener {
        void dismiss(View view);
    }
}
