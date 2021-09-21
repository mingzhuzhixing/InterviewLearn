package com.v.kotlin.ivew

import com.v.kotlin.bean.LoginBean

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