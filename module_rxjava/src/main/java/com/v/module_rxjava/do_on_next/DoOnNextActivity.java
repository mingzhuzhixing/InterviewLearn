package com.v.module_rxjava.do_on_next;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.v.module_rxjava.R;
import com.v.module_rxjava.api.UserCommonApi;
import com.v.module_rxjava.http.HttpRetrofit;
import com.v.module_rxjava.uitls.RxJavaUtils;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 需求：
 * 1、请求服务器注册操作
 * 2、注册成功后，更新注册的UI
 * 3、请求服务进行登录验证
 * 4、登录完成之后，更新登录UI
 */
@SuppressLint("CheckResult")
public class DoOnNextActivity extends AppCompatActivity {
    /**
     * 请求的api
     */
    private UserCommonApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_on_next);

        api = HttpRetrofit.createRetrofit().create(UserCommonApi.class);
    }

    /**
     * 分开写
     */
    private void userAction1() {
        api.registerAction(new RegisterRequest())
                .compose(RxJavaUtils.applySchedulers())
                .subscribe(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        //更新注册的UI
                        //.......
                    }
                });

        api.loginAction(new LoginRequest())
                .compose(RxJavaUtils.applySchedulers())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        //更新登录UI
                        //.......
                    }
                });
    }

    /**
     * 和起来写
     * <p>
     * 需求：增加弹框需求
     * * 1、请求服务器注册操作
     * * 2、注册成功后，更新注册的UI
     * * 3、请求服务进行登录验证
     * * 4、登录完成之后，更新登录UI
     */
    private void userAction2() {
        api.registerAction(new RegisterRequest())//todo 1、请求服务器注册操作
                .subscribeOn(Schedulers.io()) //上面分配 异步 线程
                .observeOn(AndroidSchedulers.mainThread()) //下面分配 UI 线程
                .doOnNext(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        //todo 2、注册成功后，更新注册的UI
                    }
                })
                .observeOn(Schedulers.io())// 给下面分配 异步 线程
                .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() {
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
                        //todo 3、请求服务进行登录验证
                        return api.loginAction(new LoginRequest());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())  // 给下面分配 UI 线程
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        //todo 4、登录完成之后，更新登录UI
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //todo 5、完成 杀青
                    }
                });
    }
}