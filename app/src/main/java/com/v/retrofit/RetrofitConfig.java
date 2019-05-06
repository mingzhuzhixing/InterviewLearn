package com.v.retrofit;

import retrofit2.Retrofit;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-25 11:27
 */

public class RetrofitConfig {

    public static Retrofit getBaseRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
