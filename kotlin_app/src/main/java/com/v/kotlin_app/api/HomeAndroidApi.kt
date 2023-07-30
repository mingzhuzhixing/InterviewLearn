package com.v.kotlin_app.api

import com.v.kotlin_app.bean.BannerDataBean
import com.v.kotlin_app.bean.HomeDataBean
import com.v.kotlin_app.http.HttpResponse
import io.reactivex.Observable
import retrofit2.http.*

interface HomeAndroidApi {


    /**
     * 首页banner
     * https://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    fun getBannerData(): Observable<HttpResponse<List<BannerDataBean>>>

    /**
     * 首页数据
     * https://www.wanandroid.com/article/list/0/json
     */
    @GET("article/list/0/json")
    fun getHomeData(): Observable<HttpResponse<HomeDataBean>>
}