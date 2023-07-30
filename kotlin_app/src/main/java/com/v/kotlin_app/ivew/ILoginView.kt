package com.v.kotlin_app.ivew

import com.v.kotlin_app.bean.LoginBean

interface ILoginView {
    /**
     * 登录成功
     */
    fun onLoginSuccess(data:LoginBean)

    /**
     * 登录失败
     */
    fun onLoginFailure(message: String)
}