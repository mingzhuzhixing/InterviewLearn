package com.v.kotlin_app.base

import android.os.Bundle

abstract class BaseFragment<P : IBasePresenter> : com.v.module_base.BaseFragment() {
    protected var mPresenter: P? = null

    //2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
    }

    /**
     * 子类实现
     */
    abstract fun createPresenter(): P?

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.unAttachView()
    }
}