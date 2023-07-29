package com.v.module_rxjava.operator

import android.view.View
import com.v.module_base.BaseTitleBarActivity
import com.v.module_rxjava.R
import com.v.module_utils.LogUtils
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiPredicate
import io.reactivex.functions.BooleanSupplier
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * 功能操作符：
 *
 * 辅助被观察者(Observable) 发送事件时实现一些功能性需求，如错误处理，线程调度
 */
@Suppress("ObjectLiteralToLambda", "CheckResult")
class FunctionOperatorActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "功能操作符"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_function_operator
    }

    /**
     * ==================subscribe 操作符===========================
     *
     * 连接被观察者和观察者
     */
    fun subscribe(view: View) {
        //创建被观察者
        val observable: Observable<Any> = Observable.create(object : ObservableOnSubscribe<Any> {
            override fun subscribe(emitter: ObservableEmitter<Any>) {
                emitter.onNext("事件")
            }
        })

        //创建观察者
        val observer: Observer<Any> = object : Observer<Any> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("subscribe onSubscribe()")
            }

            override fun onNext(value: Any) {
                LogUtils.i("subscribe 发送事件的数量  :$value")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("subscribe onError()")
            }

            override fun onComplete() {
                LogUtils.i("subscribe onComplete()")
            }
        }
        //通过subscribe 进行 被观察者(Observable)与观察者(Observer)的连接
        observable.subscribe(observer)
    }

    /**
     * ==================delay 操作符=======================================
     *
     * 延迟发送事件
     *
     * delay有多个重载方法：
     * delay(long delay,TimeUnit unit) :指定延迟时间。 参数一：时间 ； 参数二：时间单位
     * delay(long delay, TimeUnit unit, Scheduler scheduler)  指定延迟时间&线程调度器。参数一：时间 ； 参数二：时间单位；参数三： 线程调度器
     * delay(long delay, TimeUnit unit, boolean delayError)  指定延迟时间&线程调度器。参数一：时间 ； 参数二：时间单位；参数三： 是否错误延迟
     * delay(long delay, TimeUnit unit, Scheduler scheduler, boolean delayError)  指定延迟时间&线程调度器&错误延迟。参数一：时间 ； 参数二：时间单位；
     * 参数三： 线程调度器; 参数四：是否错误延迟(若中间发生错误，是否如常执行，执行完在执行onError())
     */
    fun delay(view: View) {
        Observable.just(1, 2)
                .delay(10, TimeUnit.SECONDS)
                .subscribe(object : Consumer<Int> {
                    @Throws(Exception::class)
                    override fun accept(integer: Int) {
                        LogUtils.i("delay accept:$integer")
                    }
                })
    }

    /** ====================onErrorReturn() 操作符 ======================
     *
     * 可以捕获错误。遇到错误时，发送一个特殊事件，并且正常终止.注意后面的事件不会再发送
     */
    fun onErrorReturn(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onError(Throwable("Throwable"))
                emitter.onNext(3)
            }
        }).onErrorReturn(object : Function<Throwable, Int> {
            override fun apply(t: Throwable): Int {
                return 400
            }
        }).subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("onErrorReturn onSubscribe()")
            }

            override fun onNext(t: Int) {
                LogUtils.i("onErrorReturn onNext()-->$t")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("onErrorReturn onError()")
            }

            override fun onComplete() {
                LogUtils.i("onErrorReturn onComplete()")
            }
        })
    }


    /**
     * ====================onExceptionResumeNext()/onErrorResumeNext() 操作符 ======================
     *
     * 遇到错误时发送一个新的Observable 。并且正常终止.注意原Observable后面的事件不会再发送
     * 如果捕获Exception的话使用onExceptionResumeNext() ，捕获错误的用onErrorResumeNext()
     */
    fun onExceptionResumeNext(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onError(NullPointerException("NullPointerException"))
                emitter.onNext(3)
            }
        }).onExceptionResumeNext(object : Observable<Int?>() {
            override fun subscribeActual(observer: Observer<in Int?>) {
                observer.onNext(4)
                observer.onNext(5)
            }
        }).subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("onExceptionResumeNext onSubscribe()")
            }

            override fun onNext(integer: Int) {
                LogUtils.i("onExceptionResumeNext onNext()-->$integer")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("onExceptionResumeNext onError()")
            }

            override fun onComplete() {
                LogUtils.i("onExceptionResumeNext onComplete()")
            }
        })
    }


    /**
     * ====================retry() 操作符 ======================
     *
     * 作用是：出现错误时，让被观察者重新发送数据
     * 注：若发送错误，则一直重新发送
     *
     * 有几个重载方法：
     * retry() ： 出现错误时，让被观察者重新发送数据。若错误一直发生，则一直重新发送
     * retry(long time)：与retry不同的书，若错误一直发生，被观察者则一直重新发送数据，但这持续重新发送有次数限制
     * retry(Predicate predicate) ：  出现错误时，根据指定逻辑(可以捕获到发生的错误)决定是否让被观察者重新发送数据
     * retry(new BiPredicate<Integer></Integer>, Throwable>)：出现错误时，根据指定逻辑(可以捕获重发的次数和发生的错误)决定是否让被观察者重新发送数据
     * retry（long time,Predicate predicate） ： 出现错误时，根据指定逻辑(可以捕获到发生的错误)决定是否让被观察者重新发送数据。并且有持续重发的次数限制
     */
    fun retry(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onError(Throwable("发生错误了"))
                emitter.onNext(3)
            }
        }).retry(object : BiPredicate<Int?, Throwable?> {
            override fun test(interger: Int, throwable: Throwable): Boolean {
                // interger 为重试次数 ，throwable 为捕获到的异常
                LogUtils.i("retry:" + throwable.message + " --重试次数->$interger")
                //return  true ： 重新发送请求(若持续遇到错误，就持续重新发送)
                //return  false ： 不重新发送数据 并且调用观察者的onError()方法结束
                return if (interger > 2) false else true
            }
        }).subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("onExceptionResumeNext onSubscribe()")
            }

            override fun onNext(integer: Int) {
                LogUtils.i("onExceptionResumeNext onNext()-->$integer")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("onExceptionResumeNext onError()")
            }

            override fun onComplete() {
                LogUtils.i("onExceptionResumeNext onComplete()")
            }
        })
    }

    /**
     * ===================retryUntil() 操作符============================
     *
     * 发送事件遇到错误，指定规则是否重新发送。retry(Predicate predicate)。
     *
     * return true ： 不重新发送请求，并且调用观察者的onError()方法结束
     * return false ： 重新发送数据(若持续遇到错误，就持续重新发送)
     */
    fun retryUntil(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onError(Throwable("发生错误了"))
                emitter.onNext(3)
            }
        }).retryUntil(object : BooleanSupplier {
            override fun getAsBoolean(): Boolean {
                //return true ： 不重新发送请求，并且调用观察者的onError()方法结束
                // return false ： 重新发送数据(若持续遇到错误，就持续重新发送)
                return true
            }
        }).subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("retryUntil onSubscribe()")
            }

            override fun onNext(integer: Int) {
                LogUtils.i("retryUntil onNext()-->$integer")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("retryUntil onError()")
            }

            override fun onComplete() {
                LogUtils.i("retryUntil onComplete()")
            }
        })
    }

    /**
     * ===================retryWhen() 操作符============================
     *
     * 遇到错误时，将发生的错误传递给一个新的被观察者(Observable)，并决定是否需要重新订阅原始被观察者(Observable) &  发送事件
     */
    fun retryWhen(view: View) {
        Observable.create(object : ObservableOnSubscribe<Int> {
            override fun subscribe(emitter: ObservableEmitter<Int>) {
                emitter.onNext(1)
                emitter.onNext(2)
                emitter.onError(Throwable("发送了错误"))
                emitter.onNext(3)
            }
        }).retryWhen(object : Function<Observable<Throwable>, ObservableSource<Any>> {
            override fun apply(throwableObservable: Observable<Throwable>): ObservableSource<Any> {
                return throwableObservable.flatMap(object : Function<Throwable, ObservableSource<Any>> {
                    override fun apply(t: Throwable): ObservableSource<Any> {
                        //1、若返回的Observable发送的事件 = Error ，则原始的Observable则不重新发送事件。该异常信息可在观察者的onError中获得
                        //return Observable.error(throwable);

                        //2、若返回的Observable发送的事件= Next事件(和next的内容无关)，则原始的Observable重新发送事件(若持续遇到错误，则持续发送)
                        return Observable.just(5); //仅仅是作为一个触发重新订阅原被观察者的通知，什么数据并不重要，只有不是onComplete/onError事件
                    }
                })
            }
        }).subscribe(object : Observer<Int> {
            override fun onSubscribe(d: Disposable) {
                LogUtils.i("retryWhen onSubscribe()")
            }

            override fun onNext(integer: Int) {
                LogUtils.i("retryWhen onNext()-->$integer")
            }

            override fun onError(e: Throwable) {
                LogUtils.i("retryWhen onError()")
            }

            override fun onComplete() {
                LogUtils.i("retryWhen onComplete()")
            }
        })
    }

    /**
     * ===============repeat() 操作符==============
     *
     * repeat操作符的作用是重复发射 observable的数据序列，可以使无限次也可以是指定次数.不传时为重复无限次
     */
    fun repeat(view: View) {
        Observable.just(1, 2, 3)
                .repeat(3)
                .subscribe(object : Consumer<Int> {
                    override fun accept(t: Int?) {
                        LogUtils.i("repeat accept()-->$t")
                    }
                })
    }


    /**
     * ===============repeatWhen() 操作符==============
     *
     * 将原始 Observable 停止发送事件的标识（Complete（） / Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
     * ，以此决定是否重新订阅 & 发送原来的 Observable
     */
    fun repeatWhen(view: View) {
        Observable.just(1, 2, 4)
                .repeatWhen(object : Function<Observable<Any>, ObservableSource<Any>> {
                    override fun apply(objectObservable: Observable<Any>): ObservableSource<Any> {
                        return objectObservable.flatMap(object : Function<Any, ObservableSource<Any>> {
                            override fun apply(t: Any): ObservableSource<Any> {
                                //若新被观察者（Observable）返回1个Complete()/  Error()事件，则不重新订阅 & 发送原来的 Observable
                                //Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）
                                return Observable.empty()

                                // return Observable.error(new Throwable("不再重新订阅事件"));
                                // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                                // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                                // return Observable.just(1);
                                // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                            }
                        });
                    }
                }).subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        LogUtils.i("repeatWhen onSubscribe()")
                    }

                    override fun onNext(integer: Int) {
                        LogUtils.i("repeatWhen onNext()-->$integer")
                    }

                    override fun onError(e: Throwable) {
                        LogUtils.i("repeatWhen onError()")
                    }

                    override fun onComplete() {
                        LogUtils.i("repeatWhen onComplete()")
                    }
                })
    }

    /**
     * ===============debounce() 操作符==============
     *
     * 一定的时间内没有操作就会发送事件（只会发送最后一次操作的事件）。
     * 以下的例子： 发送5个事件，每个事件间隔1秒。但是debounce限定了2秒内没有任何操作才会真正发送事件。所以只有最后一次满足条件，只能接收到事件 5
     */
    fun debounce(view: View) {
        Observable.intervalRange(1, 5, 0, 1, TimeUnit.SECONDS)
                .debounce(2, TimeUnit.SECONDS)
                .subscribe(object : Consumer<Long> {
                    override fun accept(t: Long?) {
                        LogUtils.i("debounce accept()--->$t")
                    }
                })
    }

    /**
     * ===============subscribeOn() 操作符==============
     * ===============observerOn() 操作符==============
     *
     * subscribeOn : 发送事件的线程
     * observerOn： 接收事件的线程
     *
     * 线程调度器：
     * Schedulers.io(): 代表io操作的线程，通常用于网络，读写文件等io密集型的操作
     * Schedulers.compucation(): 代表CPU计算密集型的操作，例如需要大量计算的操作
     * Schedulers.newThread(): 代表一个常规的新线程
     * AndroidSchedulers。mainThread(): 代表Android的主线程
     */
    fun subscribeOn_observerOn(view: View) {
        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                emitter.onNext("事件")
                LogUtils.i("subscribeOn_ObserverOn 发送事件: ${Thread.currentThread().name}")
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Consumer<String> {
                    override fun accept(t: String?) {
                        LogUtils.i("subscribeOn_ObserverOn", "接收事件: ${Thread.currentThread().name}");
                    }
                })
    }

}