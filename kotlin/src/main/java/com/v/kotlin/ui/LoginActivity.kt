package com.v.kotlin.ui

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.v.kotlin.KotlinMainActivity
import com.v.kotlin.R
import com.v.kotlin.base.BaseActivity
import com.v.kotlin.bean.LoginBean
import com.v.kotlin.ivew.ILoginView
import com.v.kotlin.presenter.LoginPresenterImpl
import com.v.kotlin.uitls.SpUtils
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录
 */
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
        btn_login.setOnClickListener(mClickListener)
        tv_forget_pwd.setOnClickListener(mClickListener)
        tv_reigster.setOnClickListener(mClickListener)
    }

    val mClickListener = object : View.OnClickListener {
        override fun onClick(view: View) {
            when (view.id) {
                R.id.btn_login -> {
                    //点击登录
                    val name = et_name.text.trim().toString()
                    val pwd = et_pwd.text.trim().toString()
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                        Toast.makeText(this@LoginActivity, "账号或密码不能为空", Toast.LENGTH_SHORT).show()
                        return
                    }
                    mPresenter.login(this@LoginActivity, name, pwd)
                }

                R.id.tv_forget_pwd -> {
                    //忘记密码
                }

                R.id.tv_reigster -> {
                    //注册
                    startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
                }
            }
        }
    }

    override fun onLoginSuccess(data: LoginBean) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()
        SpUtils.getInstance().put("is_login", true)
        startActivity(Intent(this@LoginActivity, KotlinMainActivity::class.java))
        finish()
    }

    override fun onLoginFailure(message: String) {
        SpUtils.getInstance().put("is_login", false)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}