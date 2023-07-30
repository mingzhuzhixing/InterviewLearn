package com.v.kotlin_app.ivew

import com.v.kotlin_app.bean.LoginBean

interface IRegisterView {
    /**
     * 注册成功
     */
    fun onRegisterSuccess(data:LoginBean)

    /**
     * 注册失败
     */
    fun onRegisterFailure(message: String)
}