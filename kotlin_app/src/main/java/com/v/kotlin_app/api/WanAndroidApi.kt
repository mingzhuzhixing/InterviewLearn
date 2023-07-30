package com.v.kotlin_app.api

import com.v.kotlin_app.bean.LoginBean
import com.v.kotlin_app.http.HttpResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WanAndroidApi {

    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") name: String,
              @Field("password") pwd: String): Observable<HttpResponse<LoginBean>>

    /**
     * 注册
     * username,password,repassword
     */
    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("username") name: String,
                 @Field("password") pwd: String,
                 @Field("repassword") rePwd: String): Observable<HttpResponse<LoginBean>>

    //todo ------------------------------------------------------------------------下面是协程api
    /**
     * 协程 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    suspend fun loginCoroutine(@Field("username") name: String,
              @Field("password") pwd: String): HttpResponse<LoginBean>

    /**
     * 协程 注册
     * username,password,repassword
     */
    @POST("user/register")
    @FormUrlEncoded
    suspend fun registerCoroutine(@Field("username") name: String,
                 @Field("password") pwd: String,
                 @Field("repassword") rePwd: String): HttpResponse<LoginBean>
}