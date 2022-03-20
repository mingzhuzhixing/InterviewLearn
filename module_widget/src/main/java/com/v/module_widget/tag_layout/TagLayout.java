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

            //1.2、根据子view计算和指定自己的布局

            //这段话执行之后就可以获取子view的宽高了，因为会调用子view的onMeasure()
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            //margin值 ViewGroup.LayoutParams没有  就用系统的MarginLayoutParams
            //想想 LinearLayout 为什么有？
            // 1、LinearLayout有自己定义的LayoutParams， 2、会腹泻一个重要的方法
            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();

            //什么时候需要换行 注意：需要考虑子view的margin
            if (lineWidth + (childView.getMeasuredWidth() + params.rightMargin + params.leftMargin) > width) {
                //需要换行
                height += childView.getMeasuredHeight() + params.bottomMargin + params.topMargin;
                lineWidth = childView.getMeasuredWidth() + params.rightMargin + params.leftMargin;
                lineChildViews = new ArrayList<>();
                mChildViews.add(lineChildViews);
            } else {
                //不需要换行
                lineWidth += childView.getMeasuredWidth() + params.rightMargin + params.leftMargin;
                maxHeight = Math.max(childView.getMeasuredHeight() + params.bottomMargin + params.topMargin, maxHeight);
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
            for (View childView : childViews) {
                ViewGroup.MarginLayoutParams childViewParams = (MarginLayoutParams) childView.getLayoutParams();
                left += childViewParams.leftMargin;
                int childTop = top + childViewParams.topMargin;
                right = left + childView.getMeasuredWidth();
                bottom = childTop + childView.getMeasuredHeight();
                //摆放位置
                childView.layout(left, childTop, right, bottom);
                //left 叠加
                left += childView.getMeasuredWidth() + childViewParams.rightMargin;
            }
            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childViews.get(0).getLayoutParams();
            top += childViews.get(0).getMeasuredHeight() + params.topMargin + params.bottomMargin;
        }
    }

    public void setTagList(List<String> tagList) {
        for (String s : tagList) {

        }
    }

}
//问题：
//1、高度有可能不一致
//2、数据肯定都是从后台获取的值,设置tag用adapter的人方式去弄
