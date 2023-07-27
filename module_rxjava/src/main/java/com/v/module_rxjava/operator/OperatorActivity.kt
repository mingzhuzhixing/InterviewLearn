package com.v.module_rxjava.operator

import android.content.Intent
import android.view.View
import com.v.module_base.BaseTitleBarActivity
import com.v.module_rxjava.R

/**
 * 操作符
 */
class OperatorActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "操作符"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_operator
    }

    //筛选操作符
    fun filterClick(view: View) {
        startActivity(Intent(this, FilterActivity::class.java))
    }
}