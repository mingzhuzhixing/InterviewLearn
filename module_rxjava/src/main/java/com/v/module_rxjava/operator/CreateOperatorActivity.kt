package com.v.module_rxjava.operator

import android.view.View
import com.v.module_base.BaseTitleBarActivity
import com.v.module_rxjava.R
import com.v.module_utils.LogUtils
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

/**
 * 创建操作符：
 *
 * 创建被观察者（Observable）对象&发送事件。包括 ： create(), just()，fromArray(),fromIterable(),timer(),
 * interval(),intervalRange(),range(),rangeLong(),nerver(),empty(),defer()等。
 */
@Suppress("ObjectLiteralToLambda", "CheckResult")
class CreateOperatorActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "创建操作符"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_create_operator
    }

    /**
     *  =======================基本用法Create====================================
     *
     *  Observable 被观察者
     *  ObservableOnSubscribe  观察者与被观察者的桥接(事件发射器)
     *  ObServer 观察者
     *   被观察者  -->  观察者与被观察者的桥接  --> 观察者
     *   被观察者.create(观察者与被观察者的桥接).subscribe(观察者)
     */
    fun createClick(view: View) {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                for (i in 0 until 10) {
                    emitter.onNext(i.toString())
                }
                emitter.onComplete()
            }
        }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("create onSubscribe()")
            }

            override fun onNext(t: String) {
                LogUtils.i("create onNext() value->$t")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("create onError()->${e.message}")
            }

            override fun onComplete() {
                LogUtils.i("create onComplete()")
            }
        })
    }

    /**
     * ====================just 操作符 ====================
     *
     * 此操作符的作用是将传入的数据依次发送出去.最多可以传10个参数
     * 以下代码会依次把 1-10的字符串发送出去。执行10此观察者的onNext方法，最后默认执行onComplete方法
     */
    fun justClick(view: View) {
        Observable.just("1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10")
                .subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {
                        LogUtils.i("just onSubscribe()")
                    }

                    override fun onNext(t: String) {
                        LogUtils.i("just onNext() value->$t")
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.i("just onError()->${e.message}")
                    }

                    override fun onComplete() {
                        LogUtils.i("just onComplete()")
                    }
                })
    }

    /**
     * ====================fromIterable 操作符 ====================
     *
     * 此操作符的作用是将传入的数组集合按脚标依次发送出去
     * 以下代码会依次把 0-9的字符串发送出去。执行10此观察者的onNext方法，最后默认执行onComplete方法
     */
    fun fromIterable(view: View) {
        val list: MutableList<String> = ArrayList()
        for (i in 0..9) {
            list.add(i.toString())
        }
        Observable.fromIterable(list)
                .subscribe(object : Consumer<String> {
                    override fun accept(t: String?) {
                        LogUtils.i("fromIterable accept() value->$t")
                    }
                })
    }

    /**
     * ==========================timer操作符 ==============================
     * 延迟指定时间发送一个0数值(Long类型)
     * timer操作符主要运行在一个新线程中，也可以自定义线程调度器(第三个参数)
     */
    fun timer(view: View) {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(object : Consumer<Long> {
                    override fun accept(t: Long?) {
                        LogUtils.i("timer accept() value->$t")
                    }
                })
    }

    /**
     * ====================fromArray 操作符============================
     *
     * 对一个数组集合进行观察，把数组一次性发给观察者，只会执行一次观察者的onNext，最后默认执行onComplete方法
     */
    fun fromArray(view: View) {
        val list: MutableList<String> = ArrayList()
        for (i in 0..9) {
            list.add(i.toString())
        }
        Observable.fromArray<List<String>>(list)
                .subscribe(object : Observer<List<String?>> {
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(strings: List<String?>) {
                        LogUtils.i("fromArray onNext() value->${strings}")
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {
                        LogUtils.i("fromArray onComplete()")
                    }
                })
    }

    /**
     * ====================interval  定时器====================
     *
     * 这个相当于定时器，用它可以取代CountDownTimer。它会按照设定的间隔时间，每次发送一个事件，发送的事件序列：默认从0开始，无限递增的整数序列
     *
     * 以下代码输出：   0 ----(5秒后)-----1-----(5秒后)------2---------(5秒后)--------3-------(5秒后)-----.......
     */
    var disposable: Disposable? = null
    fun interval(view: View) {
        disposable = Observable.interval(2, TimeUnit.SECONDS)
                .subscribe(object : Consumer<Long> {
                    override fun accept(t: Long) {
                        LogUtils.i("interval accept() value->$t")//从0开始输出
                        if (t > 30 && !disposable?.isDisposed!!) {
                            LogUtils.i("interval accept() 停止倒计时")
                            disposable?.dispose()
                        }
                    }
                })
    }

    /**
     * intervalRange  操作符
     * 作用和interval相同，但可以指定发送数据的数量
     */
    fun intervalRange(view: View) {
        /**
         * 参数1：起始发送值
         * 参数2：发送数量
         * 参数3：首次发送延迟事件
         * 参数4：每次发送事件间隔
         * 参数5：时间单位
         */
        Observable.intervalRange(2, 10, 3, 1, TimeUnit.SECONDS)
                .subscribe(object : Consumer<Long> {
                    override fun accept(t: Long?) {
                        LogUtils.i("intervalRange accept() value->$t")//从2开始输出
                    }
                })
    }

    /**
     * Range  操作符
     * 作用发送指定范围的序列，可指定范围.作用类似intervalRange，但不同的是range是无延迟发送
     */
    fun range(view: View) {
        Observable.range(2, 6)
                .subscribe(object : Consumer<Int> {
                    override fun accept(t: Int?) {
                        LogUtils.i("range accept() value->$t")//从2开始输出
                    }
                })
    }

}