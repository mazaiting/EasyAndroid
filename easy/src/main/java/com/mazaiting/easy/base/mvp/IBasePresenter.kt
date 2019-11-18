package com.mazaiting.easy.base.mvp

import java.io.Serializable

/**
 * MVP 中的 Presenter接口
 * @author mazaiting
 * @date 2018/2/5
 */

interface IBasePresenter<T>: Serializable {
    /**
     * 绑定View
     * @param view rootView
     */
    fun attachView(view: T)

    /**
     * 与View解除绑定
     */
    fun detachView()
}
