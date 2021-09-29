package com.v.kotlin.ui

import android.content.Intent
import com.v.kotlin.KotlinMainActivity
import com.v.kotlin.R
import com.v.kotlin.base.BaseActivity
import com.v.kotlin.presenter.EmptyPresenter
import com.v.kotlin.uitls.SpUtils

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