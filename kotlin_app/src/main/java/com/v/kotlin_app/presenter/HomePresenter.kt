package com.v.kotlin_app.presenter

import com.v.kotlin_app.base.IBasePresenter
import com.v.kotlin_app.bean.BannerDataBean
import com.v.kotlin_app.bean.HomeDataBean
import com.v.kotlin_app.ivew.IHomeView
import com.v.kotlin_app.model.HomeModelImpl

/**
 * ClassName: HomePresenter
 * Description:
 * @package_name com.v.kotlin_app.presenter
 * @author zhuming
 * @date 2021/9/30 4:24 下午
 */
interface IHomePresenter : IBasePresenter {
    fun getHomeData()
    fun getBannerData()

    interface HomeListener {
        fun onGainBannerSuccess(list: List<BannerDataBean>)
        fun onGainBannerFailure(msg: String)
        fun onGainSuccess(dataBean: HomeDataBean)
        fun onGainFailure(msg: String)
    }
}

class HomePresenterImpl(var homeView: IHomeView?) : IHomePresenter, IHomePresenter.HomeListener {

    private val homeModel = HomeModelImpl()

    override fun getBannerData() {
        homeModel.requestBannerData(this)
    }

    override fun getHomeData() {
        homeModel.requestHomeData(this)
    }

    override fun unAttachView() {
        homeView = null
        homeModel.requestCancel()
    }

    override fun onGainBannerSuccess(list: List<BannerDataBean>) {
        homeView?.onBannerSuccess(list)
    }

    override fun onGainBannerFailure(msg: String) {
    }

    override fun onGainSuccess(dataBean: HomeDataBean) {
        homeView?.onSuccessData(dataBean)
    }

    override fun onGainFailure(msg: String) {
    }
}