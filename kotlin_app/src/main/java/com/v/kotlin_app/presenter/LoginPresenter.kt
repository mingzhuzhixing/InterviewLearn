package com.v.kotlin_app.presenter

import android.content.Context
import com.v.kotlin_app.base.IBasePresenter
import com.v.kotlin_app.bean.LoginBean
import com.v.kotlin_app.ivew.ILoginView
import com.v.kotlin_app.model.LoginModelImpl

interface ILoginPresenter : IBasePresenter {
    fun login(context: Context, userName: String, userPwd: String)

    interface OnLoginLister {

        fun loginSuccess(data: LoginBean)

        fun loginFailure(message: String)
    }
}


class LoginPresenterImpl(var loginView: ILoginView?) : ILoginPresenter, ILoginPresenter.OnLoginLister {

    private val loginModel = LoginModelImpl()

    override fun login(context: Context, userName: String, userPwd: String) {
        loginModel.loginAction(context, userName, userPwd, this)
    }

    override fun unAttachView() {
        loginView = null
        loginModel.cancelLogin()
    }

    override fun loginSuccess(data: LoginBean) {
        loginView?.onLoginSuccess(data)
    }

    override fun loginFailure(message: String) {
        loginView?.onLoginFailure(message)
    }
}