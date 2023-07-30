package com.v.kotlin_app.basic_grammar

import com.v.kotlin_app.R
import com.v.module_base.BaseTitleBarActivity

class ForActivity : BaseTitleBarActivity() {
    override fun setTitle(): String {
        return "for循环"
    }

    override fun getLayoutId() = R.layout.activity_for
}