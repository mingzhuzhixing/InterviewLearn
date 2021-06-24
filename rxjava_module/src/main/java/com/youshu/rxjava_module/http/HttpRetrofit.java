package com.youshu.rxjava_module.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
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

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
