package com.v.kotlin_app.ui

import android.content.Intent
import com.v.kotlin_app.KotlinMainActivity
import com.v.kotlin_app.R
import com.v.kotlin_app.base.BaseActivity
import com.v.kotlin_app.presenter.EmptyPresenter
import com.v.kotlin_app.uitls.SpUtils

class SplashActivity : BaseActivity<EmptyPresenter>() {

    override fun getLayoutId() = R.layout.activity_splash

    override fun initData() {
    }

    override fun createPresenter(): EmptyPresenter {
        return EmptyPresenter()
    }

    override fun processLogical() {
        val isLogin = SpUtils.getInstance().getBooleanValue("is_login")
        //判断是否登录
        if (isLogin) {
            startActivity(Intent(this, KotlinMainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}