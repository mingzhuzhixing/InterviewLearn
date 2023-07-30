package com.v.kotlin_app.ivew

import com.v.kotlin_app.bean.BannerDataBean
import com.v.kotlin_app.bean.HomeDataBean

interface IHomeView {
    /**
     * 首页数据
     */
    fun onSuccessData(data: HomeDataBean)

    fun onBannerSuccess(data: List<BannerDataBean>)
}