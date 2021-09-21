package com.v.kotlin.ui

import com.v.kotlin.R
import com.v.kotlin.base.BaseActivity
import com.v.kotlin.bean.LoginBean
import com.v.kotlin.ivew.ILoginView
import com.v.kotlin.presenter.LoginPresenterImpl

class LoginActivity : BaseActivity<LoginPresenterImpl>(), ILoginView {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initData() {

    }

    override fun createPresenter(): LoginPresenterImpl {
        return LoginPresenterImpl(this)
    }

    override fun processLogical() {
        mPresenter.login(this, "", "")
    }

    override fun onLoginSuccess(data: LoginBean) {

    }

    override fun onLoginFailure(message: String) {

    }
}