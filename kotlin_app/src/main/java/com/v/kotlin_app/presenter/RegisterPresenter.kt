package com.v.kotlin_app.presenter

import android.content.Context
import com.v.kotlin_app.base.IBasePresenter
import com.v.kotlin_app.bean.LoginBean
import com.v.kotlin_app.ivew.IRegisterView
import com.v.kotlin_app.model.RegisterModelImpl

interface IRegisterPresenter : IBasePresenter {
    fun register(context: Context, userName: String, userPwd: String)

    interface OnRegisterLister {

        fun registerSuccess(data: LoginBean)

        fun registerFailure(message: String)
    }
}


class RegisterPresenterImpl(var registerView: IRegisterView?) : IRegisterPresenter, IRegisterPresenter.OnRegisterLister {

    private val registerModel = RegisterModelImpl()

    override fun register(context: Context, userName: String, userPwd: String) {
        registerModel.registerAction(context, userName, userPwd, this)
    }

    override fun unAttachView() {
        registerView = null
        registerModel.cancelRegister()
    }

    override fun registerSuccess(data: LoginBean) {
        registerView?.onRegisterSuccess(data)
    }

    override fun registerFailure(message: String) {
        registerView?.onRegisterFailure(message)
    }
}