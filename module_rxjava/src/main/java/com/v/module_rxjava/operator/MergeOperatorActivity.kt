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
import io.reactivex.functions.BiConsumer
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit


/**
 * 合并操作符:
 *
 * 组合多个被观察者(Observable)&合并需要发送的事件。
 * 包含：concatMap(),concat(), merge(),mergeArray(),concateArray(),reduce(),collect(),startWith(),zip(),count()。
 */
@Suppress("ObjectLiteralToLambda", "CheckResult")
class MergeOperatorActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "合并操作符"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_merge_operator
    }

    /**
     * ========================merge 操作符 ======================================
     *
     * merge操作符是把多个Observable合并成一个进行发射。merge可能会让合并到Observable的数据顺序发生错乱(组合被观察者数量<=4个)(并行无序)
     * mergeArray操作符和merge作用一样，但不同的是组合被观察者数量>4个)(并行无序)
     */
    fun merge(view: View) {
        val observable1: Observable<Int> = Observable.just(1, 2, 3)
        val observable2: Observable<String> = Observable.just("哈哈", "嘻嘻", "啊啊")
        Observable.merge(observable1, observable2)
                .delay(1, TimeUnit.SECONDS)
                .subscribe(object : Observer<Any> {

                    override fun onSubscribe(d: Disposable) {
                        LogUtils.i("merge onSubscribe()")
                    }

                    override fun onNext(t: Any) {
                        LogUtils.i("merge onNext()-->$t")
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.i("merge onError()")
                    }

                    override fun onComplete() {
                        LogUtils.i("merge onComplete()")
                    }
                })
    }

    /**
     * ========================concat 操作符 ======================================
     *
     * concat操作符也是把多个Observable合并成一个进行发射。但concat则保证合并的每个Observable的事件按顺序发射出去。(组合被观察者数量<=4个)(串行有序)
     * concatArray操作符和concat作用一样，但不同的是组合被观察者数量>4个)(串行有序)
     */
    fun concat(view: View) {
        val observable1: Observable<Int> = Observable.just(1, 2, 3)
        val observable2: Observable<String> = Observable.just("哈哈", "嘻嘻", "啊啊")
        Observable.concat(observable1, observable2)
                .delay(1, TimeUnit.SECONDS)
                .subscribe(object : Observer<Any> {

                    override fun onSubscribe(d: Disposable) {
                        LogUtils.i("concat onSubscribe()")
                    }

                    override fun onNext(t: Any) {
                        LogUtils.i("concat onNext()-->$t")
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.i("concat onError()")
                    }

                    override fun onComplete() {
                        LogUtils.i("concat onComplete()")
                    }
                })
    }


    /**
     * ========================concatDelayError()/mergeDelayError() 操作符 ======================================
     *
     * 这两个操作符的作用是： 使用concat()和merge()操作符时，若其中一个被观察者发送onError事件，则会马上终止其它被观察者继续发送事件。所以呐，这时使用concatError()/
     * mergeDelayError()事件可以使onError事件推迟到其它被观察者发送事件结束后在再触发
     */
    fun concatDelayError(view: View) {
        val observable: Observable<Int> = Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onError(NullPointerException())
                emitter.onNext(3)
                emitter.onNext(4)
            }
        })

        val justObservable: Observable<Int> = Observable.just(5, 6)

        Observable.concatArrayDelayError(observable, justObservable)
                .subscribe(object : Observer<Int> {

                    override fun onSubscribe(d: Disposable) {
                        LogUtils.i("concatArrayDelayError onSubscribe()")
                    }

                    override fun onNext(t: Int) {
                        LogUtils.i("concatArrayDelayError onNext()-->$t")
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.i("concatArrayDelayError onError()")
                    }

                    override fun onComplete() {
                        LogUtils.i("concatArrayDelayError onComplete()")
                    }
                })
    }

    /**
     * ========================zip 操作符 ======================================
     *
     * 把多个Observable合并后，并且把这些Observable的数据进行转换再发射出去。转换之后的数据数目由最短数据长度的那个Observable决定。发射完最终会自动调用观察者的onComplete方法()
     * 如以下代码： 数据长度为4的observable1和数据长度为3的observable2进行合并转换后，观察者只接收到3个数据
     */
    fun zip(view: View) {
        val observable1: Observable<Int> = Observable.just(1, 2, 3, 4)
        val observable2: Observable<String> = Observable.just("哈哈", "嘻嘻", "啊啊")
        Observable.zip(observable1, observable2, object : BiFunction<Int?, String?, String?> {
            override fun apply(integer: Int, s: String): String {
                return s + integer
            }
        }).subscribe(object : Observer<String?> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("zip onSubscribe()")
            }

            override fun onNext(t: String) {
                LogUtils.i("zip onNext()-->$t")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("zip onError()")
            }

            override fun onComplete() {
                LogUtils.i("zip onComplete()")
            }
        })
    }

    /**
     * ========================combineLatest 操作符 ======================================
     *
     * 当两个Observable 中的任何一个发送了数据，将先发送了数据的Observable的最新（最后）一个数据和另一个Observable发送的每个数据结合，最终基于该结合的结果发送数据
     *
     * 与zip()的区别： zip()是按个数合并，即1对1合并；而combineLatest()是基于时间合并，，即在同一时间点上合并
     *
     * combineLatestDelayError->作用类似于concatDelayError() / mergeDelayError(),用于错误处理
     */
    fun combineLatest(view: View) {
        val justObservable: Observable<Int> = Observable.just(1, 2, 3)
        val intervalObservable: Observable<Long> = Observable.intervalRange(1, 4, 2, 1, TimeUnit.SECONDS)
        Observable.combineLatest(justObservable, intervalObservable, object : BiFunction<Int, Long, String> {
            override fun apply(t1: Int, t2: Long): String {
                return "合并后的数据为：$t1 $t2"
            }
        }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("combineLatest onSubscribe()")
            }

            override fun onNext(t: String) {
                LogUtils.i("combineLatest onNext()-->$t")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("combineLatest onError()")
            }

            override fun onComplete() {
                LogUtils.i("combineLatest onComplete()")
            }
        })
    }


    /**
     * ======================reduce  操作符=================================
     *
     * 把被观察者需要发送的数据按照指定规则聚合成一个数据发送
     * 聚合的规则需要我们编写，内部流程是前两个数据按照我们的规则合并后，再与后面的数据按规则合并，依次类推。这样说有点抽象，看下面的例子。
     */
    fun reduce(view: View) {
        Observable.just(1, 2, 3, 4, 5)
                .reduce(object : BiFunction<Int, Int, Int> {
                    override fun apply(t1: Int, t2: Int): Int {
                        LogUtils.i("本次合并的过程是： $t1+$t2")
                        return t1 + t2
                    }
                })
                .subscribe(object : Consumer<Int?> {
                    override fun accept(integer: Int?) {
                        LogUtils.i("reduce 最终计算的结果是 :  $integer")
                    }
                })
    }


    /**
     * ========================collect 操作符=================================
     *
     * 作用是把 Observable(被观察者)发送的事件收集到一个数据结构中
     */
    fun collect(view: View) {
        Observable.just(1, 2, 3, 4, 5)
                .collect(object : Callable<ArrayList<Int?>?> {
                    @Throws(Exception::class)
                    override fun call(): ArrayList<Int?> {
                        return ArrayList()
                    }
                }, object : BiConsumer<ArrayList<Int?>?, Int?> {
                    @Throws(Exception::class)
                    override fun accept(integers: ArrayList<Int?>?, integer: Int?) {
                        integers?.add(integer)
                    }
                })
                .subscribe(object : Consumer<ArrayList<Int?>?> {
                    override fun accept(t: ArrayList<Int?>?) {
                        LogUtils.i("collect :$t")
                    }
                })
    }

    /**
     * ========================startWith/startWithArray 操作符=================================
     *
     * 在一个被观察者发送时间前，追加发送一些数据/一个新的被观察者
     */
    fun startWith(view: View) {
        Observable.just(7, 8, 9)
                .startWith(6) //在发送序列去追加单个数据
                .startWithArray(4, 5) //在发送序列去追加多个数据
                .startWith(Observable.just(1, 2, 3)) //在发送序列去追加单个被观察者
                .subscribe(object : Consumer<Int> {
                    override fun accept(t: Int) {
                        LogUtils.i("startWith :$t")
                    }
                })
    }

    /**
     * ========================count 操作符=================================
     *
     * 统计被观察者发送事件数量
     */
    fun count(view: View) {
        Observable.just(1, 2, 3, 4)
                .count()
                .subscribe(object : Consumer<Long> {
                    override fun accept(t: Long) {
                        LogUtils.i("count 发送事件的数量  :$t")
                    }
                })
    }
}