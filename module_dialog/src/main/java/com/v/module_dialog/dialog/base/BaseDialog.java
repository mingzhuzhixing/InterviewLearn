package com.v.module_dialog.dialog.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.StyleRes;

import com.v.module_base.BaseApplication;
import com.v.module_dialog.R;

/**
 * ClassName: BaseDialog
 * Description:
 *
 * @author zhuming
 * @package_name com.v.dialog_module.dialog
 * @date 2022/1/27 4:53 下午
 */
public class BaseDialog<T> extends Dialog {

    protected Context mContext;
    // 动画资源id
    protected int mAnimId = -1;
    // 显示位置
    protected int mGravity = -1;
    protected boolean isFullScreen = false;

    public BaseDialog(Context context) {
        super(context, R.style.CustomBottomDialog);
        mContext = context;
    }

    public BaseDialog(Context context ,@StyleRes int style) {
        super(context, style);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Window window = getWindow();
        if (window != null) {
            if (mGravity != -1) {
                window.setGravity(mGravity);
            } else {
                window.setGravity(Gravity.CENTER);
            }
            if (mAnimId != -1) {
                window.setWindowAnimations(mAnimId);
            }
        }
    }

    /**
     * 设置动画样式
     */
    public T setWindowAnimation(int animId) {
        mAnimId = animId;
        return (T) this;
    }


    /**
     * 设置显示位置
     */
    public T setLocation(int gravity) {
        mGravity = gravity;
        return (T) this;
    }

    /**
     * 设置全屏幕显示
     */
    public T setFullScreen(boolean flag) {
        isFullScreen = flag;
        return (T) this;
    }

    /**
     * 展示弹窗
     */
    @Override
    public void show() {
        if (mContext instanceof Activity) {
            if (((Activity) mContext).isFinishing()) {
                return;
            }
        }
        if (isShowing()){
            return;
        }
        super.show();
    }

    /**
     * 销毁弹窗
     */
    @Override
    public void dismiss() {
        if (mContext == null){
            return;
        }
        if (mContext instanceof Activity){
            if (((Activity) mContext).isFinishing()) {
                return;
            }
        }
        if (!isShowing()){
            return;
        }
        super.dismiss();
    }

    /**
     * 初始化背景透明度
     */
    private float mAlpha = 0.5f;

    /**
     * 弹框位置
     */
    private int mGravityType = Gravity.CENTER;

    public void setAlpha(float alpha) {
        this.mAlpha = alpha;
    }

    public void setGravityType(int gravityType) {
        this.mGravityType = gravityType;
    }

    /**
     * 设置widown的属性
     */
    public void initWindowAttribute() {
        Window window = getWindow();
        if (null == window) {
            return;
        }
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        lp.width = dm.widthPixels;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = mGravityType;
        lp.dimAmount = mAlpha;
        window.setAttributes(lp);
    }
}
