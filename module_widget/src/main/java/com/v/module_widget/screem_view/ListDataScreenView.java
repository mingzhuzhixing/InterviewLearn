package com.v.module_widget.screem_view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class ListDataScreenView extends LinearLayout implements View.OnClickListener {
    private LinearLayout mMenuTabView;
    private FrameLayout mMenuMiddleView;

    //阴影
    private View mShadowView;

    //菜单view容器
    private FrameLayout mMenuContainerView;

    //阴影的颜色
    private final int mShadowColor = Color.parseColor("#99000000");

    public BaseMenuAdapter mAdapter;

    //内容菜单高度
    private int mMenuContainerHeight;

    //当前打开的位置
    private int mCurrentPosition = -1;

    //是否正在实行动画
    private boolean isRunningAnimator = false;

    //动画执行时间
    private final static int DURATION_TIME = 350;

    public ListDataScreenView(Context context) {
        this(context, null);
    }

    public ListDataScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListDataScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //设置当前自定义LinearLayout自布局的排列方式
        setOrientation(VERTICAL);
        initLayout(context);
    }

    /**
     * 初始化buju
     */
    private void initLayout(Context context) {
        //1、先去创建一个xml布局，在加载， findViewById
        //2、建档的效果用代码去创建

        //1.1 创建头部用来存放 tab
        mMenuTabView = new LinearLayout(context);
        mMenuTabView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mMenuTabView.setBackgroundColor(Color.WHITE);
        addView(mMenuTabView);

        //1.2 创建FrameLayout 用来存放 = 阴影(View)+菜单内容布局(FrameLayout)
        mMenuMiddleView = new FrameLayout(context);
        mMenuMiddleView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mMenuMiddleView);

        //创建阴影 可以不用设置 LayoutParams 默认就是 MATCH_PARENT  MATCH_PARENT
        mShadowView = new View(context);
        mShadowView.setBackgroundColor(mShadowColor);
        mShadowView.setAlpha(0.0f);
        mShadowView.setOnClickListener(this);
        mShadowView.setVisibility(GONE);
        mMenuMiddleView.addView(mShadowView);

        //创建菜单  存放菜单内容
        mMenuContainerView = new FrameLayout(context);
        mMenuContainerView.setBackgroundColor(Color.WHITE);
        mMenuMiddleView.addView(mMenuContainerView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (mCurrentPosition < 0) {
            mMenuContainerHeight = (int) (height * 75 / 100.0f);
            ViewGroup.LayoutParams params = mMenuContainerView.getLayoutParams();
            params.height = mMenuContainerHeight;
            mMenuContainerView.setLayoutParams(params);
            mMenuContainerView.setTranslationY(-mMenuContainerHeight);
        }
    }

    /**
     * 设置适配器
     */
    public void setAdapter(BaseMenuAdapter adapter) {
        this.mAdapter = adapter;
        //获取有多少条
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            //获取tab
            View tabView = mAdapter.getTabView(i, mMenuTabView);
            mMenuTabView.addView(tabView);
            //点击事件
            setTabClick(tabView, i);

            //获取菜单的内容
            View menuView = mAdapter.getMenuView(i, mMenuContainerView);
            menuView.setVisibility(GONE);
            mMenuContainerView.addView(menuView);
        }
    }

    /**
     * tab 点击事件
     */
    private void setTabClick(View tabView, int position) {
        tabView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mCurrentPosition == -1) {
                    //没打开
                    openMenu(tabView, position);
                } else {
                    if (mCurrentPosition == position) {
                        //点击打开和关闭的tabview 是同一个，则需要第二次点击关闭
                        closeMenu();
                    } else {
                        View menuView = mMenuContainerView.getChildAt(mCurrentPosition);
                        menuView.setVisibility(GONE);
                        //打开菜单栏
                        if (mAdapter != null) {
                            mAdapter.menuNormalStyle(mMenuTabView.getChildAt(mCurrentPosition));
                        }

                        mCurrentPosition = position;
                        menuView = mMenuContainerView.getChildAt(mCurrentPosition);
                        menuView.setVisibility(VISIBLE);
                        //打开菜单栏
                        if (mAdapter != null) {
                            mAdapter.menuSelectStyle(tabView);
                        }
                    }
                }
            }
        });
    }

    /**
     * 打开菜单
     */
    private void openMenu(View tabView, int position) {
        //获取当前位置显示当前菜单，  菜单是加到了菜单容器的

        //判断如果正在执行动画怎不进行下面的动画
        if (isRunningAnimator) {
            return;
        }
        //位移动画 透明度
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mMenuContainerView, "translationY", -mMenuContainerHeight, 0f);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 0.0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationAnimator, alphaAnimator);
        animatorSet.setDuration(DURATION_TIME);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isRunningAnimator = true;
                mShadowView.setVisibility(VISIBLE);

                View menuView = mMenuContainerView.getChildAt(position);
                menuView.setVisibility(VISIBLE);

                //打开菜单栏
                if (mAdapter != null) {
                    mAdapter.menuSelectStyle(tabView);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isRunningAnimator = false;
                mCurrentPosition = position;
            }
        });
        animatorSet.start();
    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        //判断如果正在执行动画怎不进行下面的动画
        if (isRunningAnimator) {
            return;
        }
        //位移动画 透明度
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(mMenuContainerView, "translationY", 0, -mMenuContainerHeight);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mShadowView, "alpha", 1.0f, 0.0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationAnimator, alphaAnimator);
        animatorSet.setDuration(DURATION_TIME);
        //要等关闭动画执行完成后才能去隐藏但钱菜单
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isRunningAnimator = true;

                //关闭菜单栏
                View tabView = mMenuTabView.getChildAt(mCurrentPosition);
                if (mAdapter != null) {
                    mAdapter.menuNormalStyle(tabView);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isRunningAnimator = false;
                mShadowView.setVisibility(GONE);
                View menuView = mMenuContainerView.getChildAt(mCurrentPosition);
                menuView.setVisibility(GONE);
                mCurrentPosition = -1;
            }
        });
        animatorSet.start();
    }

    /**
     * 阴影点击关闭
     */
    @Override
    public void onClick(View v) {
        closeMenu();
    }
}
