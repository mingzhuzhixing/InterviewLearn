package com.v.module_rxjava.lifecycle

import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.v.module_base.BaseTitleBarActivity
import com.v.module_rxjava.R
import com.v.module_utils.LogUtils
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer


/**
 * RxJava的生命周期
 *
 * RxJava拥有自己执行的生命周期，在不同的时期做不同的事情可以在源码中很好地看到，在以下示例代码中看看其在生命周期中究竟做了哪些事情
 */
@Suppress("ObjectLiteralToLambda", "CheckResult")
class RxJavaLifecycleActivity : BaseTitleBarActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_rx_java_lifecycle
    }

    override fun setTitle(): String {
        return "RxJava的生命周期"
    }

    fun lifecycleClick(view: View) {
        Observable.create(object :ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onNext(3)
                emitter.onError(NullPointerException())
            }
        }).doOnEach(object : Consumer<Notification<Int>> {
            override fun accept(t: Notification<Int>?) {
                LogUtils.i("doOnEach: ${t?.value}")
            }
        }).doOnNext(object : Consumer<Int> {
            override fun accept(t: Int?) {
                LogUtils.i("doOnNext: $t")
            }
        }).doAfterNext(object : Consumer<Int> {
            override fun accept(t: Int?) {
                LogUtils.i("doAfterNext: $t")
            }
        }).doOnComplete(object : Action {
            override fun run() {
                LogUtils.i("doOnComplete")
            }
        }).doOnError(object : Consumer<Throwable> {
            override fun accept(t: Throwable?) {
                LogUtils.i("doOnError")
            }
        }).doOnTerminate(object : Action {
            override fun run() {
                LogUtils.i("doOnTerminate")
            }
        }).doAfterTerminate(object : Action {
            override fun run() {
                LogUtils.i("doAfterTerminate")
            }
        }).doOnSubscribe(object : Consumer<Disposable> {
            override fun accept(t: Disposable?) {
                LogUtils.i("doOnSubscribe")
            }
        }).doFinally(object : Action {
            override fun run() {
                LogUtils.i("doFinally")
            }
        }).subscribe(object : Observer<Int>{
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("onSubscribe")
            }

            override fun onNext(t: Int) {
                LogUtils.i("onNext: $t")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("onError")
            }

            override fun onComplete() {
                LogUtils.i("onComplete")
            }
        })
    }

}
