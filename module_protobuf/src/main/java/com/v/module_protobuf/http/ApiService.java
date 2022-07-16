package com.v.module_protobuf.http;


import com.v.module_protobuf.LoginRequestOuterClass;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ClassName: HttpSend
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_protobuf
 * @date 2022/7/16 9:08
 */
public interface ApiService {

    /**
     * 登录
     */
    @POST("login.action")
    Observable<LoginRequestOuterClass.LoginResponse> login(@Body RequestBody bytes);

    @POST("login.action")
    Observable<LoginRequestOuterClass.LoginResponse> login(@Query("username") String username, @Query("pwd") String pwd);
}
