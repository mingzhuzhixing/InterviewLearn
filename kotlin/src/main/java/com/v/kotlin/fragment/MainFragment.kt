package com.v.kotlin.fragment

import com.v.kotlin.R
import com.v.kotlin.base.BaseFragment
import com.v.kotlin.presenter.EmptyPresenter

/**
 * 首页
 */
class MainFragment : BaseFragment<EmptyPresenter>() {
    override fun getLayoutId() = R.layout.fragment_main

    override fun initData() {

    }

    override fun createPresenter(): EmptyPresenter? {
        return null
    }

    override fun processLogical() {
    }

}