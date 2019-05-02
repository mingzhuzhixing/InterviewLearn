package com.v.rxjava;

/**
 * Class description here
 *
    男生
    T 看电影
    Subscrible<? super T> 看电影的女生

    super 和 extends 区别：
    extends 向下兼容 用于返回参数类型的限定，不能用于参数类型限定
    super 用于参数类型限定，不能用于返回参数的限定

 */

public interface OnSubscrible<T> extends Action1<Subscrible<? super T>> {
}
