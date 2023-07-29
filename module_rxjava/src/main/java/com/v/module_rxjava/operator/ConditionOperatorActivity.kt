package com.v.module_rxjava.operator

import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.v.module_base.BaseTitleBarActivity
import com.v.module_rxjava.R
import com.v.module_utils.LogUtils
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import java.util.concurrent.TimeUnit


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

    /**
     * ====================takeWhile 操作符 ====================
     *
     * 发射Observable发射的数据，直到一个指定的条件不成立
     */
    fun takeWhile(view: View) {
        Observable.just(1, 2, 3, 4, 5)
                .takeWhile(object : Predicate<Int> {
                    override fun test(t: Int): Boolean {
                        return (t != 3)
                    }

                }).subscribe(object : Consumer<Int> {
                    override fun accept(t: Int?) {
                        LogUtils.i("takeWhile accept-->$t")
                    }
                })
    }

    /**
     * ====================skipWhile 操作符 ====================
     *
     * 跳过Observable发射的数据，直到一个指定的条件不成立,跳过成立条件
     */
    fun skipWhile(view: View) {
        Observable.just(1, 2, 3, 4, 5)
                .skipWhile(object : Predicate<Int> {
                    override fun test(t: Int): Boolean {
                        return (t != 3)
                    }
                }).subscribe(object : Consumer<Int> {
                    override fun accept(t: Int?) {
                        LogUtils.i("skipWhile accept-->$t")
                    }
                })
    }

    /**
     * ====================takeUntil 操作符 ====================
     *
     * 1、takeUntil（new Predicate） 执行到某个条件时，停止发送事件
     * 2、takeUntil（new Observer） takeUntil（） 传入的Observable开始发送数据，（原始）第1个Observable的数据停止发送数据
     */
    fun takeUntil(view: View) {
        Observable.just(1, 2, 3, 4, 5)
                .takeUntil(object : Predicate<Int> {
                    override fun test(t: Int): Boolean {
                        return (t == 3)
                    }

                }).subscribe(object : Consumer<Int> {
                    override fun accept(t: Int?) {
                        LogUtils.i("takeUntil accept-->$t")
                    }
                })
    }

    fun takeUntil2(view: View) {
        LogUtils.i("takeUntil2 点击了")
        Observable.interval(1, TimeUnit.SECONDS)
                // 第2个Observable：延迟5s后开始发送1个Long型数据
                .takeUntil(Observable.timer(5, TimeUnit.SECONDS))
                .subscribe(object : Consumer<Long> {
                    override fun accept(t: Long?) {
                        LogUtils.i("takeUntil2 accept-->$t")
                    }
                })
    }

    /**
     * ====================skipUntil 操作符 ====================
     *
     * 等到 skipUntil（） 传入的Observable开始发送数据，（原始）第1个Observable的数据才开始发送数据
     */
    var disposableSkipUntil: Disposable? = null
    fun skipUntil(view: View) {
        LogUtils.i("skipUntil 点击了")
        disposableSkipUntil = Observable.interval(1, TimeUnit.SECONDS)
                // 第2个Observable：延迟5s后开始发送1个Long型数据
                .skipUntil(Observable.timer(5, TimeUnit.SECONDS))
                .subscribe(object : Consumer<Long> {
                    override fun accept(t: Long?) {
                        LogUtils.i("skipUntil accept-->$t")
                    }
                })
    }

    /**
     * ====================sequenceEqual 操作符 ====================
     *
     * 判定两个Observables是否发射相同的数据序列。若相同，返回 true；否则，返回 false
     */
    fun sequenceEqual(view: View) {
        val just1 = Observable.just(1, 2, 3, 4)
        val just2 = Observable.just(1, 2, 3, 4)
        Observable.sequenceEqual(just1, just2)
                .subscribe(object : Consumer<Boolean> {
                    override fun accept(t: Boolean?) {
                        LogUtils.i("sequenceEqual accept-->$t")
                    }
                })
    }

    /**
     * ====================contains 操作符 ====================
     *
     * 判断发送的数据中是否包含指定数据
     */
    fun contains(view: View) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .contains(2)
                .subscribe(object : Consumer<Boolean> {
                    override fun accept(t: Boolean?) {
                        LogUtils.i("contains accept-->$t")
                    }
                })
    }

    /**
     * ====================isEmpty 操作符 ====================
     *
     * 判断发送的数据是否为空
     */
    fun isEmpty(view: View) {
        Observable.just(1, 2, 3, 4, 5)
                .isEmpty()
                .subscribe(object : Consumer<Boolean> {
                    override fun accept(t: Boolean?) {
                        LogUtils.i("contains accept-->$t")
                    }
                })
    }

    /**
     * ====================amb 操作符 ====================
     *
     * 当需要发送多个 Observable时，只发送 先发送数据的Observable的数据，而其余 Observable则被丢弃。
     */
    fun amb(view: View) {
        val list: MutableList<ObservableSource<Int>> = ArrayList()
        list.add(Observable.just(1, 2, 3).delay(1, TimeUnit.SECONDS))
        list.add(Observable.just(4, 5, 6))
        Observable.amb(list)
                .subscribe(object : Consumer<Int> {
                    override fun accept(t: Int?) {
                        LogUtils.i("amb accept-->$t")
                    }
                })
    }

    /**
     * ====================defaultIfEmpty 操作符 ====================
     *
     * 在不发送任何有效事件（ Next事件）、仅发送了 Complete 事件的前提下，发送一个默认值。
     */
    fun defaultIfEmpty(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onComplete()
            }
        }).defaultIfEmpty(10)
                .subscribe(object : Consumer<Int> {
                    override fun accept(t: Int?) {
                        LogUtils.i("defaultIfEmpty accept-->$t")
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (disposableSkipUntil != null && !disposableSkipUntil!!.isDisposed) {
            disposableSkipUntil!!.dispose()
        }
    }
}