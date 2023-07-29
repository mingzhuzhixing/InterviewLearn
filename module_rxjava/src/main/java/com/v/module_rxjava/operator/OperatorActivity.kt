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

    //创建操作符
    fun createClick(view: View) {
        startActivity(Intent(this, CreateOperatorActivity::class.java))
    }

    //转换操作符
    fun convertClick(view: View) {
        startActivity(Intent(this, ConvertOperatorActivity::class.java))
    }

    //合并操作符
    fun mergeClick(view: View) {
        startActivity(Intent(this, MergeOperatorActivity::class.java))
    }

    //功能操作符
    fun functionClick(view: View) {
        startActivity(Intent(this, FunctionOperatorActivity::class.java))
    }

    //筛选操作符
    fun filterClick(view: View) {
        startActivity(Intent(this, FilterOperatorActivity::class.java))
    }

    //条件操作符
    fun conditionClick(view: View) {
        startActivity(Intent(this, ConditionOperatorActivity::class.java))
    }
}