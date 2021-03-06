package com.mazaiting.easy.base.presenter

import com.mazaiting.easy.base.mvp.IBasePresenter
import com.mazaiting.easy.base.mvp.IBaseView


/**
 * 主持人基类
 * @author mazaiting
 * @date 2018/2/5
 * @description
 * V: IBaseView 子类
 */

open class BasePresenter<V: IBaseView> : IBasePresenter<V> {

    /**
     * 基类视图
     */
    open var view: V? = null

    /**
     * 与视图绑定
     */
    override fun attachView(view: V) {
        this.view = view
    }

    /**
     * 解除View的绑定
     */
    override fun detachView() {
        view?.let { view = null }
    }
}
