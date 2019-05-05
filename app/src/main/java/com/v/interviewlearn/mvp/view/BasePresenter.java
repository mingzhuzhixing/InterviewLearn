package com.v.interviewlearn.mvp.view;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 16:47
 */
public abstract class BasePresenter<T> {

    /**
     * 当内存不足时释放内存
     */
    protected WeakReference<T> mViewRef;

    /**
     * bind p with v
     * @param view
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
    }


    /**
     * 解除关联
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected T getView(){
        return mViewRef.get();
    }
}
