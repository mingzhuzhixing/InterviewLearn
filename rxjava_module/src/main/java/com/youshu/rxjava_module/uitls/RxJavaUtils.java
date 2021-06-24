package com.youshu.rxjava_module.uitls;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
}
