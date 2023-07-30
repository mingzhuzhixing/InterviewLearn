package com.v.kotlin_app.fragment

import com.v.kotlin_app.R
import com.v.kotlin_app.base.BaseFragment
import com.v.kotlin_app.presenter.EmptyPresenter

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