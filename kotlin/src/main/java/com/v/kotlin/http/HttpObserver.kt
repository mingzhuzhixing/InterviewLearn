package com.v.kotlin.http

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class HttpObserver<T> : Observer<HttpResponse<T>> {

    override fun onSubscribe(p0: Disposable) {

    }

    override fun onNext(t: HttpResponse<T>) {
        if (t.data == null) {
            onFailure(t.errorMsg)
        } else {
            onSuccess(t.data)
        }
    }

    override fun onError(e: Throwable) {
        onFailure(e.message)
    }

    override fun onComplete() {

    }

    /**
     * 成功
     */
    abstract fun onSuccess(data: T)

    /**
     * 失败
     */
    abstract fun onFailure(errorMsg: String?)
}