package com.v.module_widget.tag_layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Tag流式布局
 * 分析：
 * 1、onMeasure()指定宽高
 * 1.1、for循环测量子view
 * 1.2、根据子view计算和指定自己的布局
 * 2、onLayout()
 * 2.1、for循环摆放所有的子view
 */
public class TagLayout extends ViewGroup {
    private final List<List<View>> mChildViews = new ArrayList<>();
    private final static String TAG = "TagLayout";

    public TagLayout(Context context) {
        this(context, null);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //清空
        mChildViews.clear();
        //获取所有的子view
        int childCount = getChildCount();
        //获取到的宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int height = getPaddingTop() + getPaddingBottom();

        //一行的宽度
        int lineWidth = getPaddingLeft();

        int maxHeight = 0;

        List<View> lineChildViews = new ArrayList<>();
        mChildViews.add(lineChildViews);
        for (int i = 0; i < childCount; i++) {
            //1.1、for循环测量子view
            View childView = getChildAt(i);

            if (childView.getVisibility() == GONE) {
                //判断是否可见
                continue;
            }

            //1.2、根据子view计算和指定自己的布局

            //这段话执行之后就可以获取子view的宽高了，因为会调用子view的onMeasure()
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            //margin值 ViewGroup.LayoutParams没有  就用系统的MarginLayoutParams
            //想想 LinearLayout 为什么有？
            // 1、LinearLayout有自己定义的LayoutParams， 2、会腹泻一个重要的方法
            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
            int leftRightMargin = params.leftMargin + params.rightMargin;
            int topBottomMargin = params.topMargin + params.bottomMargin;
            //什么时候需要换行 注意：需要考虑子view的margin
            if (lineWidth + (childView.getMeasuredWidth() + leftRightMargin) > width) {
                //需要换行
                height += maxHeight;
                lineWidth = childView.getMeasuredWidth() + leftRightMargin;
                lineChildViews = new ArrayList<>();
                mChildViews.add(lineChildViews);
            } else {
                //不需要换行
                lineWidth += childView.getMeasuredWidth() + leftRightMargin;
                maxHeight = Math.max(childView.getMeasuredHeight() + topBottomMargin, maxHeight);
            }
            lineChildViews.add(childView);
        }
        height += maxHeight;
        Log.e(TAG, "width:" + width + " height:" + height + " mChildViews:" + mChildViews.size());
        setMeasuredDimension(width, height);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 摆放子view
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //2.1、for循环摆放所有的子view
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childView = getChildAt(i);
//            childView.layout(left, top, right, bottom);
//        }
        int left = 0, top = getPaddingTop(), right = 0, bottom = 0;
        for (List<View> childViews : mChildViews) {
            left = getPaddingLeft();
            int maxHeight = 0;
            for (View childView : childViews) {
                if (childView.getVisibility() == GONE) {
                    //判断是否可见
                    continue;
                }
                ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
                left += params.leftMargin;
                int childTop = top + params.topMargin;
                right = left + childView.getMeasuredWidth();
                bottom = childTop + childView.getMeasuredHeight();
                //摆放位置
                childView.layout(left, childTop, right, bottom);
                //left 叠加
                left += childView.getMeasuredWidth() + params.rightMargin;

                int childHeight = childView.getMeasuredHeight() + params.bottomMargin + params.topMargin;
                maxHeight = Math.max(maxHeight, childHeight);
            }
            top += maxHeight;
        }
    }

    /**
     * 设置adapter
     */
    public void setAdapter(TagLayoutAdapter adapter) {
        if (adapter == null) {
            //空指针异常
            throw new RuntimeException("adapter is null");
        }
        //清空所有子view
        removeAllViews();

        //获取数量
        int childCount = adapter.getCount();
        for (int i = 0; i < childCount; i++) {
            View view = adapter.getView(i, this);
            addView(view);
        }
    }

}
//问题：
//1、高度有可能不一致
//2、数据肯定都是从后台获取的值,设置tag用adapter的人方式去弄
