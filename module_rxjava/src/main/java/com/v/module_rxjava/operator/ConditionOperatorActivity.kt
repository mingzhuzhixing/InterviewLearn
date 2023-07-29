package com.v.module_rxjava.operator

import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.v.module_base.BaseTitleBarActivity
import com.v.module_rxjava.R
import com.v.module_utils.LogUtils
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate

/**
 * 条件操作符
 */
@Suppress("ObjectLiteralToLambda", "CheckResult")
class ConditionOperatorActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "条件操作符"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_condition_operator
    }

    /**
     * ====================all 操作符 ====================
     *
     * 判定是否Observable发射的所有数据都满足某个条件
     */
    fun all(view: View) {
        Observable.just(1, 2, 3, 4, 5)
                .all(object : Predicate<Int?> {
                    override fun test(integer: Int): Boolean {
                        return integer <= 10
                    }
                }).subscribe(object : Consumer<Boolean> {
                    override fun accept(t: Boolean?) {
                        LogUtils.i("all accept-->$t")
                    }
                })
    }
}