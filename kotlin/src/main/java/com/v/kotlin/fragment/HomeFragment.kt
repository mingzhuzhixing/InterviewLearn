package com.v.kotlin.fragment

import android.util.Log
import com.bumptech.glide.Glide
import com.v.kotlin.R
import com.v.kotlin.base.BaseFragment
import com.v.kotlin.bean.HomeDataBean
import com.v.kotlin.ivew.IHomeView
import com.v.kotlin.presenter.EmptyPresenter
import com.v.kotlin.presenter.HomePresenterImpl
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 首页
 */
class HomeFragment : BaseFragment<HomePresenterImpl>(), IHomeView {
    override fun getLayoutId() = R.layout.fragment_home

    override fun initData() {

    }

    override fun createPresenter(): HomePresenterImpl {
        return HomePresenterImpl(this)
    }

    override fun processLogical() {
        mPresenter?.getHomeData()
    }

    override fun onSuccessData(data: HomeDataBean) {
        Log.i("zm123", "data:${data.toString()}")
        Glide.with(mActivity).load(data.ad_list[0].image).into(iv_ad)
    }
}