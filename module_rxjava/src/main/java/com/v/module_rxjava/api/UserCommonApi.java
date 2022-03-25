package com.v.module_rxjava.api;

import com.v.module_rxjava.do_on_next.LoginRequest;
import com.v.module_rxjava.do_on_next.LoginResponse;
import com.v.module_rxjava.do_on_next.RegisterRequest;
import com.v.module_rxjava.do_on_next.RegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;

/**
 * ClassName: UserCommonApi
 * Description: 用户注册登录的api
 *
 * @author zhuming
 * @package_name com.youshu.rxjava_module.api
 * @date 2021/6/25 2:37 PM
 */
public interface UserCommonApi {
    /**
     * 注册
     */
    Observable<RegisterResponse> registerAction(@Body RegisterRequest registerRequest);

    /**
     * 登录
     */
    Observable<LoginResponse> loginAction(@Body LoginRequest loginRequest);
}
