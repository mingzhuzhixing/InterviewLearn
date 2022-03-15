package com.v.textview_module.marquee;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.v.textview_module.R;


public class MarqueeView extends HorizontalScrollView implements Runnable {
    private Context context;
    private LinearLayout mainLayout;//跑马灯滚动部分
    private int scrollSpeed = 5;//滚动速度
    private int currentX;//当前x坐标
    private int screenWidth;//屏幕宽度

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    void initView() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        mainLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.scroll_content, null);
        this.addView(mainLayout);
    }

    //开始滚动
    public void startScroll() {
        removeCallbacks(this);
        currentX = 0; // 开始滚动时候的位置
        post(this);
    }

    //停止滚动
    public void stopScroll() {
        removeCallbacks(this);
    }

    @Override
    public void run() {
        mainLayout.scrollTo(currentX, 0);
        currentX++;

        if (currentX >= screenWidth) {
            mainLayout.scrollTo(-screenWidth, 0);
            currentX = -screenWidth;
        }

        postDelayed(this, 50 / scrollSpeed);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
