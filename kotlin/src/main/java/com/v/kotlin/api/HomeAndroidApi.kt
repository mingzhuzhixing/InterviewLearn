package com.v.kotlin.api

import com.v.kotlin.bean.HomeDataBean
import com.v.kotlin.bean.LoginBean
import com.v.kotlin.http.HttpResponse
import io.reactivex.Observable
import retrofit2.http.*

interface HomeAndroidApi {

    /**
     * 首页数据
     * http://v2.ffu365.com/index.php?m=Api&c=Index&a=home&appid=2
     */
    @GET("index.php")
    fun getHomeData(@Query("m") m: String, @Query("c") c: String, @Query("a") a: String,
        @Query("appid") appid: String): Observable<HttpResponse<HomeDataBean>>
}