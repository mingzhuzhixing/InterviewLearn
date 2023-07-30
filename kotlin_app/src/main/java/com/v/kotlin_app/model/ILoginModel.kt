package com.v.kotlin_app.model

import android.content.Context
import com.v.kotlin_app.api.WanAndroidApi
import com.v.kotlin_app.bean.LoginBean
import com.v.kotlin_app.http.HttpResponse
import com.v.kotlin_app.http.HttpRetrofit
import com.v.kotlin_app.presenter.ILoginPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
//        HttpRetrofit.getInstance().createApi(WanAndroidApi::class.java)
//                .login(userName, userPwd)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : HttpObserver<LoginBean>() {
//                    override fun onSuccess(data: LoginBean) {
//                        listener.loginSuccess(data)
//                    }
//
//                    override fun onFailure(errorMsg: String?) {
//                        listener.loginFailure(errorMsg ?: "")
//                    }
//                })
        //使用协程完成网络请求
        GlobalScope.launch(Dispatchers.Main) {
            val result: HttpResponse<LoginBean> = HttpRetrofit.getInstance()
                    .createApi(WanAndroidApi::class.java)
                    .loginCoroutine(userName, userPwd)
            if (result.errorCode != -1) {
                listener.loginSuccess(result.data)
            } else {
                listener.loginFailure(result.errorMsg)
            }
        }
    }

    override fun cancelLogin() {

    }
}