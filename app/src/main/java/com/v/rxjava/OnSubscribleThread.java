package com.v.rxjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-16 16:53
 */

public class OnSubscribleThread<T> implements OnSubscrible<T> {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    Observable<T> source;


    public OnSubscribleThread(Observable<T> source) {
        this.source = source;
    }

    @Override
    public void call(final Subscrible<? super T> subscrible) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                source.subscrible(subscrible);
            }
        };

        executorService.execute(runnable);

    }
}
