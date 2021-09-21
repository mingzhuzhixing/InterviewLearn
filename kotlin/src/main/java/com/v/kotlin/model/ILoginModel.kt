package com.v.kotlin.model

import android.content.Context
import com.v.kotlin.api.WanAndroidApi
import com.v.kotlin.bean.LoginBean
import com.v.kotlin.http.HttpObserver
import com.v.kotlin.http.HttpRetrofit
import com.v.kotlin.presenter.ILoginPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ILoginModel {
    fun loginAction(context: Context, userName: String, userPwd: String, listener: ILoginPresenter.OnLoginLister)

    /**
     * 取消登录
     */
    fun cancelLogin()
}

class LoginModelImpl : ILoginModel {

    /**
     * 登录动作
     */
    override fun loginAction(context: Context, userName: String, userPwd: String, listener: ILoginPresenter.OnLoginLister) {
        HttpRetrofit.getInstance().createApi(WanAndroidApi::class.java)
                .login(userName, userPwd)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(object : HttpObserver<LoginBean>() {
                    override fun onSuccess(data: LoginBean) {
                        listener.loginSuccess(data)
                    }

                    override fun onFailure(errorMsg: String?) {
                        listener.loginFailure(errorMsg ?: "")
                    }
                })
    }

    override fun cancelLogin() {

    }
}