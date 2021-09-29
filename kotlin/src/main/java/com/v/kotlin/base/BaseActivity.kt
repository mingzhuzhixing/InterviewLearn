package com.v.kotlin.base

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : IBasePresenter> : AppCompatActivity(){

    protected lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initData()

        mPresenter = createPresenter()

        //隐藏标题栏
        hideActionBar()

        //处理逻辑
        processLogical()
    }

    /**
     * 页面layoutId
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 子类实现
     */
    abstract fun createPresenter(): P

    /**
     * 处理逻辑
     */
    abstract fun processLogical()

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