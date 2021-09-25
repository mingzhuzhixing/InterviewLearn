package com.v.kotlin.fragment

import com.v.kotlin.R
import com.v.kotlin.base.BaseFragment
import com.v.kotlin.presenter.EmptyPresenter

/**
 * 个人fragment
 */
class PersonFragment : BaseFragment<EmptyPresenter>() {

    override fun getLayoutId() = R.layout.fragment_person

    override fun initData() {
    }

    override fun createPresenter(): EmptyPresenter? {
        return null
    }

    override fun processLogical() {
    }
}