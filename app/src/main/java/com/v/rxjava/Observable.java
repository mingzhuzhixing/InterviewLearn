package com.v.rxjava;

/**
 * 黑屋子 平安夜
 *
 * T代表 看电影
 */

public class Observable<T> {

    private OnSubscrible<T> onSubscrible;

    public Observable(OnSubscrible<T> onSubscrible){
        this.onSubscrible = onSubscrible;
    }

    public static <T> Observable<T> create(OnSubscrible<T> onSubscrible){

        return new Observable<>(onSubscrible);
    }

    public void subscrible(Subscrible<? super T> subscrible){
        onSubscrible.call(subscrible);
    }

    public <R> Observable<R> map(Func1<? super T, ? extends R> func1){
        return new Observable<R>(new OnSubscribleLife<T, R>(onSubscrible, func1));
    }

    public Observable<T> subscripOnIO(){
        return create(new OnSubscribleThread<T>(this));
    }
}
