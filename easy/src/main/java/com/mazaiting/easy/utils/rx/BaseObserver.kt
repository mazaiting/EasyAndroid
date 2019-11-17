package com.mazaiting.easy.utils.rx

import androidx.annotation.NonNull
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 自定义通知
 * @author mazaiting
 * @date 2018/2/5
 */

abstract class BaseObserver<T> : Observer<T> {
    /**
     * 请求成功
     * @param t 数据
     */
    protected abstract fun onSuccess(t: T)

    /**
     * 请求失败
     * @param e 异常信息
     */
    protected abstract fun onFailed(e: Throwable)

    override fun onNext(@NonNull t: T) {
        onSuccess(t)
    }

    override fun onError(@NonNull e: Throwable) {
        onFailed(e)
    }

    override fun onSubscribe(@NonNull d: Disposable) {

    }

    override fun onComplete() {

    }
}
