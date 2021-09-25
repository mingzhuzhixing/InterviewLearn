package com.v.kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.v.kotlin.KotlinMainActivity
import com.v.kotlin.R
import com.v.kotlin.uitls.SpUtils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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