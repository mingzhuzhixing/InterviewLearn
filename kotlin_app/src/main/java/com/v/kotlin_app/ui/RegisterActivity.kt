package com.v.kotlin_app.ui

import android.text.TextUtils
import android.view.View.OnClickListener
import android.widget.Toast
import com.v.kotlin_app.R
import com.v.kotlin_app.base.BaseActivity
import com.v.kotlin_app.bean.LoginBean
import com.v.kotlin_app.ivew.IRegisterView
import com.v.kotlin_app.presenter.RegisterPresenterImpl
import kotlinx.android.synthetic.main.activity_register.*

/**
 * 注册
 */
class RegisterActivity : BaseActivity<RegisterPresenterImpl>(), IRegisterView {

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initData() {

    }

    override fun createPresenter(): RegisterPresenterImpl {
        return RegisterPresenterImpl(this)
    }

    override fun processLogical() {
        //点击登录
        btn_register.setOnClickListener(OnClickListener {
            val name = et_name.text.trim().toString()
            val pwd = et_pwd.text.trim().toString()
            val rePwd = et_repwd.text.trim().toString()
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (pwd != rePwd) {
                Toast.makeText(this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            mPresenter.register(this@RegisterActivity, name, pwd)
        })
    }

    override fun onRegisterSuccess(data: LoginBean) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onRegisterFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}