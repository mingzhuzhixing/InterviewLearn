package com.v.module_widget.view_drag_helper;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

/**
 * 垂直拖动
 */
public class VerticalViewDragListView extends FrameLayout {

    private ViewDragHelper mDragHelper;

    private View mDragListView;

    //后面菜单的高度
    private int mMenuHeight;

    //菜单是否打开
    private boolean mMenuIsOpen = false;

    public VerticalViewDragListView(@NonNull Context context) {
        this(context, null);
    }

    public VerticalViewDragListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalViewDragListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, mDragHelperCallBack);
    }

    /**
     * 拖动我的子view
     */
    private final ViewDragHelper.Callback mDragHelperCallBack = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            //指定该子view是否可以拖动  就是child
            //只能列表可以滑动,背后的不能拖动
            return mDragListView == view;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            //垂直拖动的位置
            //2.3、垂直拖动的方位只能是后面菜单的高度
            if (top <= 0) {
                top = 0;
            }
            if (top >= mMenuHeight) {
                top = mMenuHeight;
            }
            return top;
        }

        //2.2、只需要垂直拖动
//        @Override
//        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
//            //水平拖动的位置  ，目前不需要横向也可拖动，只需要垂直拖动
//            return super.clampViewPositionHorizontal(child, left, dx);
//        }

        //2.4、手指搜开的时候两者选择其一，要么打开要么关
        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            //super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild != mDragListView) {
                return;
            }
            yvel = mDragListView.getTop();//获取滑动view拖动的高度,注意yvel始终为0
            if (yvel > mMenuHeight / 2.0f) {
                //滚动到菜单的高度 （打开）
                mMenuIsOpen = true;
                mDragHelper.settleCapturedViewAt(0, mMenuHeight);
            } else {
                //滚动到0的位置 （关闭）
                mMenuIsOpen = false;
                mDragHelper.settleCapturedViewAt(0, 0);
            }
            invalidate();
        }
    };

    //现象就是 listview 可以滑动，但是菜单没有滑动效果了
    private float mDownY;

    //ViewDragHelper: Ignoring pointerId=0 because ACTION_DOWN was not received for this pointer before ACTION_MOVE.
    // It likely happened because  ViewDragHelper did not receive all the events in the event stream.
    //原因：VerticalViewDragListView.onInterceptTouchEvent().DOWN -> ListView.onTouch().DOWN ->
    // VerticalViewDragListView.onInterceptTouchEvent().MOVE -> VerticalViewDragListView.onTouchEvent.MOVE;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //菜单打开要全部拦截
        if (mMenuIsOpen) {
            return true;
        }
        //向下胡奥定拦截，不给listView做处理
        //谁拦截谁？ 父view拦击子view 但是子view可以调用requestDisallowInterceptTouchEvent()这个方法请求父view不要拦截，其实就是改变的mGroupFlags的值
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                //让DrawHelper 那一个完整的事件
                mDragHelper.processTouchEvent(ev);
                break;

            case MotionEvent.ACTION_MOVE:
                float moveY = ev.getY();
                if (moveY - mDownY > 0 && !canChildScrollUp()) {
                    //向下滑动 && 滚动到顶部，拦截不让listview做处理
                    return true;
                }
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 判断view 是否滑动到了顶部
     * 是可以继续向上滚动
     */
    public boolean canChildScrollUp() {
        if (Build.VERSION.SDK_INT < 14) {
            if (mDragListView instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mDragListView;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 ||
                        absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mDragListView, -1) || mDragListView.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mDragListView, -1);
        }
    }

    /**
     * 相应滚动
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    //第一种方式获取mMenuHeight
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        View menuView = getChildAt(0);
//        mMenuHeight = menuView.getMeasuredHeight();
//    }

    //第二种方式获取mMenuHeight
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed && getChildCount() > 0) {
            View menuView = getChildAt(0);
            mMenuHeight = menuView.getMeasuredHeight();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();

        if (childCount != 2) {
            throw new RuntimeException("VerticalViewDragListView 只能放两个子view");
        }
        //获取第二个子view
        mDragListView = getChildAt(1);

        View menuView = getChildAt(0);
        mMenuHeight = menuView.getMeasuredHeight();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }
}
