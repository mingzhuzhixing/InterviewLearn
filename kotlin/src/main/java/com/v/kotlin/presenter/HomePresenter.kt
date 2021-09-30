package com.v.kotlin.presenter

import com.v.kotlin.base.IBasePresenter
import com.v.kotlin.bean.HomeDataBean
import com.v.kotlin.ivew.IHomeView
import com.v.kotlin.model.HomeModelImpl

/**
 * ClassName: HomePresenter
 * Description:
 * @package_name com.v.kotlin.presenter
 * @author zhuming
 * @date 2021/9/30 4:24 下午
 */
interface IHomePresenter : IBasePresenter {
    fun getHomeData()

    interface HomeListener {
        fun onGainSuccess(dataBean: HomeDataBean)
        fun onGainFailure(msg: String)
    }
}

class HomePresenterImpl(var homeView: IHomeView?) : IHomePresenter, IHomePresenter.HomeListener {

    val homeModel = HomeModelImpl()

    override fun getHomeData() {
        homeModel.requestHomeData(this)
    }

    override fun unAttachView() {
        homeView = null
        homeModel.requestCancel()
    }

    override fun onGainSuccess(dataBean: HomeDataBean) {
        homeView?.onSuccessData(dataBean)
    }

    override fun onGainFailure(msg: String) {
    }
}