package com.v.kotlin.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpRetrofit {

    private object Holder {
        val instance = HttpRetrofit()
    }

    companion object {
        private const val BASE_URL: String = "https://www.wanandroid.com/"
        fun getInstance(): HttpRetrofit = Holder.instance;
    }

    init {
        init()
    }

    private lateinit var mRetrofit: Retrofit

    private fun init() {
        val mOkHttpClient = OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.SECONDS)
                .connectTimeout(10000, TimeUnit.SECONDS)
                .writeTimeout(10000, TimeUnit.SECONDS)
                .build()
        mRetrofit = Retrofit.Builder().client(mOkHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <T> createApi(api: Class<T>): T {
        return mRetrofit.create(api)
    }
}