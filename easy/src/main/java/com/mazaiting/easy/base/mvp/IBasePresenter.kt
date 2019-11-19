package com.mazaiting.easy.base.mvp

/**
 * MVP 中的 Presenter接口
 * @author mazaiting
 * @date 2018/2/5
 */

interface IBasePresenter<in V: IBaseView>{
    /**
     * 绑定View
     * @param view rootView
     */
    fun attachView(view: V)

    /**
     * 与View解除绑定
     */
    fun detachView()
}
