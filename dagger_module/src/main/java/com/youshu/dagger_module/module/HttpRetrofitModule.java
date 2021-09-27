package com.youshu.dagger_module.module;

import com.youshu.dagger_module.http.HttpRetrofit;

import dagger.Module;
import dagger.Provides;

/**
 * ClassName: HttpRetrofitModule
 * Description:
 *
 * @author zhuming
 * @package_name com.youshu.dagger_module.module
 * @date 2021/9/27 6:35 下午
 */
@Module
public class HttpRetrofitModule {

    @Provides
    public HttpRetrofit getHttpRetrofit(){
        return new HttpRetrofit();
    }
}
