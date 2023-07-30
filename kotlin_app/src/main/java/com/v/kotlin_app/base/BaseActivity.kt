package com.v.kotlin_app.base

abstract class BaseActivity<P : IBasePresenter> : com.v.module_base.BaseActivity() {

    protected lateinit var mPresenter: P

    override fun getBaseLayoutId(): Int {
        return getLayoutId()
    }

    /**
     * 初始化Presenter
     */
    override fun initPresenter() {
        mPresenter = createPresenter()
    }

    /**
     * 初始化Actionbar显示状态
     */
    override fun initActionbarStatus(): Boolean {
        return true
    }

    /**
     * 页面layoutId
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 子类实现
     */
    abstract fun createPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unAttachView()
    }
}