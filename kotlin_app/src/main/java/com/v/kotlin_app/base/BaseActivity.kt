package com.v.kotlin_app.base

import androidx.appcompat.app.ActionBar

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

    override fun initActionBar() {
        //隐藏标题栏
        hideActionBar()
    }

    /**
     * 页面layoutId
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 子类实现
     */
    abstract fun createPresenter(): P

    /**
     * 隐藏actionbar
     */
    fun hideActionBar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }

    /**
     * 显示actionbar
     */
    fun showActionBar() {
        supportActionBar?.hide()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.unAttachView()
    }
}