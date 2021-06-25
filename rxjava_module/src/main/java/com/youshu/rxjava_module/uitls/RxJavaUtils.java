package com.youshu.rxjava_module.uitls;

import android.view.View;

import com.jakewharton.rxbinding2.view.RxView;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: RxJavaUtils
 * Description:
 *
 * @author zhuming
 * @package_name com.youshu.rxjava_module
 * @date 2021/6/24 6:12 PM
 */
public class RxJavaUtils {

    public static <UD> ObservableTransformer<UD, UD> applySchedulers() {
        return new ObservableTransformer<UD, UD>() {
            @Override
            public ObservableSource<UD> apply(Observable<UD> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 防抖封装
     * 2秒内执行一次
     */
    public static Observable<Object> clickView(@NonNull View view) {
        checkNoNull(view);
        return RxView.clicks(view).throttleFirst(2, TimeUnit.SECONDS);
    }

    /**
     * 查空
     */
    private static <T> void checkNoNull(T value) {
        if (value == null) {
            throw new NullPointerException("generic value here is null");
        }
    }
}
