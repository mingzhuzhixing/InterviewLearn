package com.v.kotlin_app.basic_grammar

import android.view.View
import com.v.kotlin_app.R
import com.v.kotlin_app.spread_function.format
import com.v.module_base.BaseTitleBarActivity
import com.v.module_utils.LogUtils
import java.util.*

class ForActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "for循环"
    }

    override fun getLayoutId() = R.layout.activity_for

    override fun processLogical() {
        super.processLogical()
        print(Date().format())
    }

    /**
     * date 扩展函数使用
     */
    fun dateClick(view: View) {
        LogUtils.i(Date().format())
    }
}