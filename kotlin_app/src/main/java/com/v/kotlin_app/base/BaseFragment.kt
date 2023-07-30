package com.v.kotlin_app.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : IBasePresenter> : Fragment() {
    protected var mPresenter: P? = null

    /**
     * 获取Activity对象
     */
    protected lateinit var mActivity: Activity

    //1
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity;
    }

    //2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
    }

    //3
    /**
     * 如果 rootView为空 怎使用else中的空白布局
     * super.onCreateView(inflater, container, savedInstanceState)
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
        return rootView ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    //4
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
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
    abstract fun createPresenter(): P?

    /**
     * 处理逻辑
     */
    abstract fun processLogical()


    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.unAttachView()
    }
}