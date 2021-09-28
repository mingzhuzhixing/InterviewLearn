package com.v.kotlin.fragment

import com.v.kotlin.R
import com.v.kotlin.base.BaseFragment
import com.v.kotlin.bean.Student
import com.v.kotlin.ivew.ICollectView
import com.v.kotlin.presenter.CollectPresenterImpl
import com.v.kotlin.presenter.EmptyPresenter

/**
 * 收藏
 */
class CollectFragment : BaseFragment<CollectPresenterImpl>(), ICollectView {
    override fun getLayoutId() = R.layout.fragment_collect

    override fun initData() {
    }

    override fun createPresenter(): CollectPresenterImpl = CollectPresenterImpl(this)

    override fun processLogical() {
        mPresenter?.requestQueryAll()
    }

    override fun showAllStudent(data: List<Student>) {

    }

    override fun deleteAllStudent() {

    }
}