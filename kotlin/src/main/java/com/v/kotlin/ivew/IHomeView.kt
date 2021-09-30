package com.v.kotlin.ivew

import com.v.kotlin.bean.HomeDataBean

interface IHomeView {
    /**
     * 首页数据
     */
    fun onSuccessData(data: HomeDataBean)
}