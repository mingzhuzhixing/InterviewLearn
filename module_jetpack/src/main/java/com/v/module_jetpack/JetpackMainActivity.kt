package com.v.module_jetpack

import android.content.Intent
import android.view.View
import com.v.module_base.BaseTitleBarActivity
import com.v.module_jetpack.paging.PagingActivity

class JetpackMainActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "JetpackMain"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_jetpack_main
    }

    fun pagingClick(view: View) {
        startActivity(Intent(this, PagingActivity::class.java))
    }
}