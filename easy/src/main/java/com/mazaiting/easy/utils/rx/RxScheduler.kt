package com.mazaiting.easy.utils.rx

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 调度线程控制
 * @author mazaiting
 * @date 2018/2/5
 */
object RxScheduler {

    /**
     * 创建了调度线程
     * 设置订阅线程为io线程及通知为主线程
     */
    fun applySchedulers(): ObservableTransformer<Any, Any>
            = ObservableTransformer { upstream ->
        upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
