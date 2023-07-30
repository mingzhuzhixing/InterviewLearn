package com.v.kotlin_app.model

import android.content.Context
import com.v.kotlin_app.api.WanAndroidApi
import com.v.kotlin_app.bean.LoginBean
import com.v.kotlin_app.http.HttpObserver
import com.v.kotlin_app.http.HttpRetrofit
import com.v.kotlin_app.presenter.IRegisterPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IRegisterModel {
    fun registerAction(context: Context, userName: String, userPwd: String, listener: IRegisterPresenter.OnRegisterLister)

    /**
     * 取消登录
     */
    fun cancelRegister()
}

class RegisterModelImpl : IRegisterModel {

    /**
     * 登录动作
     */
    override fun registerAction(context: Context, userName: String, userPwd: String, listener: IRegisterPresenter.OnRegisterLister) {
        HttpRetrofit.getInstance().createApi(WanAndroidApi::class.java)
                .register(userName, userPwd, userPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : HttpObserver<LoginBean>() {
                    override fun onSuccess(data: LoginBean) {
                        listener.registerSuccess(data)
                    }

                    override fun onFailure(errorMsg: String?) {
                        listener.registerFailure(errorMsg ?: "")
                    }
                })
    }

    override fun cancelRegister() {

    }
}