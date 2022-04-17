package com.v.kotlin.model

import com.v.kotlin.api.HomeAndroidApi
import com.v.kotlin.api.WanAndroidApi
import com.v.kotlin.bean.BannerDataBean
import com.v.kotlin.bean.HomeDataBean
import com.v.kotlin.bean.LoginBean
import com.v.kotlin.http.HomeHttpRetrofit
import com.v.kotlin.http.HttpObserver
import com.v.kotlin.http.HttpRetrofit
import com.v.kotlin.presenter.IHomePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * ClassName: HomeModel
 * Description:
 * @package_name com.v.kotlin.model
 * @author zhuming
 * @date 2021/9/30 4:27 下午
 */
interface IHomeModel {
    fun requestBannerData(listener: IHomePresenter.HomeListener)
    fun requestHomeData(listener: IHomePresenter.HomeListener)
    fun requestCancel()
}

class HomeModelImpl : IHomeModel {
    /**
     * 获取banner数据
     */
    override fun requestBannerData(listener: IHomePresenter.HomeListener) {
        HomeHttpRetrofit.getInstance().createApi(HomeAndroidApi::class.java)
                .getBannerData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : HttpObserver<List<BannerDataBean>>() {
                    override fun onSuccess(data: List<BannerDataBean>) {
                        listener.onGainBannerSuccess(data)
                    }

                    override fun onFailure(errorMsg: String?) {
                        listener.onGainBannerFailure(errorMsg ?: "")
                    }
                })
    }

    override fun requestHomeData(listener: IHomePresenter.HomeListener) {
        HomeHttpRetrofit.getInstance().createApi(HomeAndroidApi::class.java)
            .getHomeData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpObserver<HomeDataBean>() {
                override fun onSuccess(data: HomeDataBean) {
                    listener.onGainSuccess(data)
                }

                override fun onFailure(errorMsg: String?) {
                    listener.onGainFailure(errorMsg ?: "")
                }
            })
    }

    override fun requestCancel() {

    }
}