package com.mazaiting.easy.base.mvp

/**
 * 加载进度接口
 */
interface ILoading {
    /**
     * 显示进度条
     */
    fun onShowLoading()

    /**
     * 隐藏进度条
     */
    fun onHideLoading()
}