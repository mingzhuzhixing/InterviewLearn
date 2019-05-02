package com.v.rxjava;

/**
 * 好兄弟 实现男生接口
 *
 * T 代表那个想找个开房的女生
 * R 代表能够开房的女生
 */

public class OnSubscribleLife<T, R> implements OnSubscrible<R> {
    /**
     * 持有上一个男生的引用
     */
    OnSubscrible<T> boy;

    /**
     * 男生想找一个能够开放的女生
     *
     * 碍于面子，又只能把这个消息传递给基佬
     */
    Func1<? super T, ? extends R> transfromer;

    public OnSubscribleLife(OnSubscrible<T> boy, Func1<? super T, ? extends R> transfromer) {
        this.boy = boy;
        this.transfromer = transfromer;
    }

    /**
     * 想看电影的女生
     * @param subscrible
     */
    @Override
    public void call(Subscrible<? super R> subscrible) {
        Subscrible<? super T> wifi = new OperaChange<>(subscrible, transfromer);
        boy.call(wifi);
    }

    /**
     * 他老婆
     * @param <T>
     * @param <R>
     */
    class OperaChange<T, R> extends Subscrible<T>{

        Subscrible<? super R> actual;
        Func1<? super T, ? extends R> transfrom;

        public OperaChange(Subscrible<? super R> actual, Func1<? super T, ? extends R> transfrom) {
            this.actual = actual;
            this.transfrom = transfrom;
        }

        @Override
        public void onNext(T t) {
            /**
             * 自己替换闺蜜
             */
            R r=this.transfrom.call(t);
            actual.onNext(r);
        }
    }
}
