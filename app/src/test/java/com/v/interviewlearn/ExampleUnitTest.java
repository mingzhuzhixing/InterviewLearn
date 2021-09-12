package com.v.interviewlearn;


import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void rxJavaTest() {
        /**
         * Observable---被观察着
         * create---操作符
         * ObservableEmitter---发射器向观察者发送事件
         *
         */
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("zm");
                //emitter.onComplete();
//                emitter.onError(new Throwable());
            }
        });


        /**
         * 观察者
         */
        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("---onSubscribe---");
            }

            @Override
            public void onNext(String s) {
                System.out.println("---onNext---"+s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("---onError---");
            }

            @Override
            public void onComplete() {
                System.out.println("---onComplete---");
            }
        };

        //订阅
        stringObservable.subscribe(observer);
    }

    @Test
    public void textRxJava(){
        //ObservableJust  ObservableSubscribeOn ObservableObserveOn 都是Observable的子类
        Observable<String> observableJust = Observable.just("111"); //返回 ObservableJust
        Observable<String> observableSubscribeOn = observableJust.subscribeOn(Schedulers.io());//返回 ObservableSubscribeOn
        Observable<String> observableObserveOn = observableSubscribeOn.observeOn(AndroidSchedulers.mainThread());//返回 ObservableObserveOn
        observableObserveOn.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });



        Observable.just("111")//返回被观察者  1
                .subscribeOn(Schedulers.io())//返回被观察者  指定被观察者在那个线程中调用  12
                .observeOn(AndroidSchedulers.mainThread()) //返回被观察者  123
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}