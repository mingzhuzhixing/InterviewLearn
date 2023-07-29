package com.v.module_rxjava.operator

import android.view.View
import com.v.module_base.BaseTitleBarActivity
import com.v.module_rxjava.R
import com.v.module_utils.LogUtils
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import java.util.concurrent.TimeUnit

/**
 * 过滤操作符：
 *
 * 用于将Observable发送的数据进行过滤和选择。让Observable返回我们所需要的数据。
 * 过滤操作符有buffer()，filter()，skip()，take()，skipLast()，takeLast()，throttleFirst()，distainctUntilChange()。
 */
@Suppress("ObjectLiteralToLambda", "CheckResult")
class FilterOperatorActivity : BaseTitleBarActivity() {

    override fun setTitle(): String {
        return "过滤操作符"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_filter
    }

    /**
     * filter 操作符
     */
    fun filter(view: View) {
        //将[1,10]筛选出整除2的整数
        Observable.range(0, 10).filter(object : Predicate<Int> {
            override fun test(t: Int): Boolean {
                return t % 2 == 0
            }
        }).subscribe(object : Consumer<Int> {
            override fun accept(t: Int?) {
                LogUtils.i("filter value->$t")
            }
        })
    }

    /**
     * distinct主要用于对ObservableSource实现去重的操作，其核心原理在于HashCode对比
     *
     * distinct的核心原理在于：
     * 1、Function<@NonNull T, @NonNull R>的实现用于生成唯一的HashCode。
     * 2、(Supplier)HashSetSupplier.INSTANCE实现HashCode作为key，判断是否可以添加。
     * 3、DistinctObserver负责利用Function<@NonNull T, @NonNull R>、(Supplier)HashSetSupplier.INSTANCE进行数据的去重。
     *
     * distinct直接使用
     */
    fun distinct(view: View) {
        //(1,2,5,4,5) 去重后为：(1,2,5,4)
        Observable.just(1, 2, 5, 4, 5)
            .distinct()
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int?) {
                    LogUtils.i("distinct value->$t")
                }
            })
    }

    /**
     * distinct自定义去重
     *
     * 对于一些需要自定义去重效果的过滤，需要用到Observable<T>distinct(@NonNull Function<?super T, K> keySelector)
     */
    fun distinct2(view: View) {
        // 筛选只出现一次
        Observable.just(1, 2, 3, true, 4, 10.0f, false, 6.1f)
            .distinct(object : Function<Any, String> {
                override fun apply(t: Any): String {
                    return when (t) {
                        is Int -> {
                            "I"
                        }
                        is Boolean -> {
                            "B"
                        }
                        else -> {
                            "F"
                        }
                    }
                }
            })
            .subscribe(object : Consumer<Any> {
                override fun accept(t: Any) {
                    LogUtils.i("distinct2 value->$t")
                }
            })
    }

    /**
     * take的核心思想在于对于可观察的上游序列的数据，从起始数据开始仅仅获取满足某些指定条件的指定数据，当不满足后，往后的数据都不再获取，直接抛弃。
     */
    fun take(view: View) {
        //[1,100]过滤[1,10]
        Observable.range(1, 100)
            .take(10) //指定个数
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int?) {
                    LogUtils.i("take value->$t")
                }
            })
    }

    /**
     * takeTime的核心在于takeUntil的实现，而takeUntil属于一种布尔操作符，其核心思想在于直到某个条件成立的时候不再发射数据。
     * takeUntil的核心是由TakeUntilPredicateObserver实现的
     * 输出 0,1
     */
    fun takeTime(view: View) {
        Observable.interval(1, TimeUnit.SECONDS)//每1秒发射一个数据
            .take(3, TimeUnit.SECONDS)//获取3秒内发射的数据
            .subscribe(object : Consumer<Long> {
                override fun accept(t: Long?) {
                    LogUtils.i("takeTime value->$t")
                }
            })
    }

    /**
     * skip是指在源数据中从起始数据开始判断是否满足一些逻辑条件，直至不满足条件时，开始将上游数据捕获发送到下游
     * 输出 4,5，6,7,8,9
     */
    fun skip(view: View) {
        Observable.range(1, 9)
            .skip(3) // 表示跳过小于3的数
            .subscribe(object : Consumer<Int> {
                override fun accept(t: Int?) {
                    LogUtils.i("skip value->$t")
                }
            })
    }

    /**
     * 输出 4,5
     */
    fun skipTime(view: View) {
        Observable.interval(1, TimeUnit.SECONDS)//每1秒发射一个数据
            .take(5)//获取5秒内发射的数据
            .skip(3, TimeUnit.SECONDS) // 表示跳过小于3的数
            .subscribe(object : Consumer<Long> {
                override fun accept(t: Long?) {
                    LogUtils.i("skipTime value->$t")
                }
            })
    }
}