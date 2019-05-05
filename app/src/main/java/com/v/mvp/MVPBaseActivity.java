package com.v.mvp;

import android.app.Activity;
import android.os.Bundle;

import com.v.mvp.view.BasePresenter;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 16:44
 */
public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends Activity {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter=createPresenter();

        //内存泄漏
        //关联view
        mPresenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除关联
        mPresenter.detachView();
    }

    protected abstract T createPresenter();
}
