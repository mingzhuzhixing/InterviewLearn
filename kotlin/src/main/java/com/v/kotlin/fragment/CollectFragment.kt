package com.v.kotlin.fragment

import com.v.kotlin.R
import com.v.kotlin.base.BaseFragment
import com.v.kotlin.presenter.EmptyPresenter

/**
 * 收藏
 */
class CollectFragment : BaseFragment<EmptyPresenter>() {
    override fun getLayoutId() = R.layout.fragment_collect

    override fun initData() {
    }

    override fun createPresenter(): EmptyPresenter? {
        return null
    }

    override fun processLogical() {
    }
}