package com.mazaiting.easy.base.presenter

import com.mazaiting.easy.base.mvp.IBasePresenter
import com.mazaiting.easy.base.mvp.IBaseView


/**
 * 主持人基类
 * @author mazaiting
 * @date 2018/2/5
 */

open class BasePresenter : IBasePresenter {

    /**
     * 基类视图
     */
    private var mView: IBaseView? = null

    /**
     * 与视图绑定
     */
    override fun attachView(view: IBaseView) {
        this.mView = view
    }

    /**
     * 解除View的绑定
     */
    override fun detachView() {
        if (null != mView) {
            mView = null
        }
    }
}
