package com.v.rxjava;

import android.graphics.Bitmap;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-16 14:20
 */

public class RxJavaTest {
    public static void main(String[] args) {
//        rxJavaCreate();
        rxJavaCreate1();
    }

    private static void rxJavaCreate(){
        Observable.create(new OnSubscrible<String>() {
            @Override
            public void call(Subscrible<? super String> subscrible) {
                subscrible.onNext("男生：走看电影去");
                System.out.println("男生：走看电影去");
            }
        }).subscrible(new Subscrible<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("女生：可以那就一起去看电影吧");
            }
        });
    }

    /**
     * string 代表看电影
     * 男生：想得到开放的女生
     * 不关心兄弟是 如何把老婆给他的  他老婆是怎样把 闺蜜给他的
     * bitmap 开放
     * url  不需要关系--->bitmap
     */
    private static void rxJavaCreate1(){
        Observable.create(new OnSubscrible<String>() {
            @Override
            public void call(Subscrible<? super String> subscrible) {
                subscrible.onNext("男生：走看电影去");
                System.out.println("男生：看电影去");
                System.out.println("线程："+ Thread.currentThread().getName());
            }
        }).map(new Func1<String, Bitmap>() {

            @Override
            public Bitmap call(String s) {
                System.out.println("s=="+s );
                System.out.println("老婆：  不愿意和你开放" );
                return null;
            }
        }).subscripOnIO().subscrible(new Subscrible<Bitmap>() {
            @Override
            public void onNext(Bitmap s) {
                System.out.println("开放的女生：走 开放去");
            }
        });
    }
}
