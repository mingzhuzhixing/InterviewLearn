package com.youshu.rxjava_module.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ClassName: HttpRetrofit
 * Description:
 *
 * @author zhuming
 * @package_name com.youshu.rxjava_module.http
 * @date 2021/6/24 6:40 PM
 */
public class HttpRetrofit {
    private static final String baseUrl = "https://www.wanandroid.com/";

    public static Retrofit createRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.writeTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                //配置回调库，采用RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //配置转化库，默认是Gson
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
