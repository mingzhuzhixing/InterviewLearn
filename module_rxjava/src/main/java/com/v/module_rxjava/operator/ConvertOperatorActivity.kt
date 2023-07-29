package com.v.module_rxjava.operator

import android.view.View
import com.v.module_base.BaseTitleBarActivity
import com.v.module_rxjava.R
import com.v.module_utils.LogUtils
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit


/**
 * 转换操作符：
 *
 * 变换被观察者(Observable)发送的事件。将Observable发送的数据按照一定的规则做一些变换，然后再将变换的数据发射出去。
 * 变换的操作符有map,flatMap，concatMap,switchMap,buffer,groupBy等等。
 */
@Suppress("ObjectLiteralToLambda", "CheckResult")
class ConvertOperatorActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "转换操作符"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_convert_operator
    }

    /**
     * ======================map 操作符============================
     *
     * map操作符，可以说是的被观察者转换器。 通过指定一个Funcation对象，将被观察者(Observable)转换成新的被观察者(Observable)对象并发射，观察者会收到新的被观察者并处理
     * 本来发射的数据是 数字1，然后观察者接收到的是 “ 这是新的观察数据===： 1”
     * 流程：  被观察者.create(事件发射器).map(转换器).subscribe(观察者)
     */
    fun map(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                for (i in 0..9) {
                    emitter.onNext(i)
                }
                emitter.onComplete()
            }
        }).map(object : Function<Int, String?> {
            override fun apply(t: Int): String {
                return "这是新的观察数据===：$t"
            }
        }).subscribe(object : Consumer<String?> {
            override fun accept(s: String?) {
                LogUtils.i("map-->$s")
            }
        })
    }

    /**
     * ======================flatMap============================
     *
     * flatMap操作符，将Observable每一次发射的事件都转换成一个Observable，也就是说把Observable的发射事件集合转换成Observable集合。
     * 然后观察者Observer最终观察的是Observable集合。但是观察者不能保证接收到这Observable集合发送事件的顺序。
     *
     * 是不是很抽象？ 先来看看这一个流程：  观察者.create(事件发射器).flatMap(转换器).subscribe(观察者)
     *
     * 再来看看例子：下面的代码，一开始Observable通过发射器的onNext发送了0-9这10个事件发送出去，正常来说Observer接收到就是 0 - 9 这10个数据
     * 然而中间经过了flatMap的转换。这 10个事件都分别在Funcation转换器上新的Observable。而最终观察者接收的就是这10个新的Observable的发送事件。
     */
    fun flatMap(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                for (i in 0..9) {
                    emitter.onNext(i)
                }
                emitter.onComplete()
            }
        }).flatMap(object : Function<Int, ObservableSource<String>> {
            override fun apply(t: Int): ObservableSource<String> {
                val list: MutableList<String> = ArrayList()
                list.add("4--->$t")
                list.add("5--->$t")
                return Observable.fromIterable(list).delay(100, TimeUnit.MILLISECONDS)
            }
        }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("flatMap onSubscribe()")
            }

            override fun onNext(t: String) {
                LogUtils.i("flatMap onNext()-->$t")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("flatMap onError()")
            }

            override fun onComplete() {
                LogUtils.i("flatMap onComplete()")
            }
        })
    }

    /**
     * ======================concatMap============================
     *
     * 与上面的flatMap作用基本一样，与flatMap唯一不同的是concat能保证Observer接收到Observable集合发送事件的顺序
     */
    fun concatMap(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                for (i in 0..9) {
                    emitter.onNext(i)
                }
                emitter.onComplete()
            }
        }).concatMap(object : Function<Int, ObservableSource<String>> {
            override fun apply(t: Int): ObservableSource<String> {
                val list: MutableList<String> = ArrayList()
                list.add("3--->$t")
                list.add("4--->$t")
                return Observable.fromIterable(list).delay(100, TimeUnit.MILLISECONDS)
            }
        }).subscribe(object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("concatMap onSubscribe()")
            }

            override fun onNext(t: String) {
                LogUtils.i("concatMap onNext()-->$t")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("concatMap onError()")
            }

            override fun onComplete() {
                LogUtils.i("concatMap onComplete()")
            }
        })
    }


    /**
     * ========================buffer 操作符 ======================================
     *
     * 把发射数据按照一定间隔分成若干段。按每段的数据转换成新的Observable，这个Observable把一段数据一次性发射出去。
     * 可以简单地理解为把一组数据分成若干小组发射出去，而不是单个单个地发射出去
     */
    fun buffer(view: View) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .buffer(2)
                .subscribe(object : Observer<List<Int?>?> {
                    override fun onSubscribe(d: Disposable) {
                        LogUtils.i("buffer onSubscribe()")
                    }

                    override fun onNext(integers: List<Int?>) {
                        for (integer in integers) {
                            LogUtils.i("buffer onComplete():" + integer.toString())
                        }
                        LogUtils.i("buffer onComplete()=========")
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.i("buffer onError()")
                    }

                    override fun onComplete() {
                        LogUtils.i("buffer onComplete()")
                    }
                })
    }
}