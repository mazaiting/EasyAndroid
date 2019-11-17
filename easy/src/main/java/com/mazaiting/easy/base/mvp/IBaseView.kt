package com.mazaiting.easy.base.mvp

/**
 * MVP 中的 View 接口
 * @author mazaiting
 * @date 2018/2/5
 */

interface IBaseView {
    /**
     * 显示正在加载中...
     */
    fun onShowLoading()

    /**
     * 请求成功
     */
    fun onShowSuccess()

    /**
     * 显示请求失败
     * @param message 错误信息
     */
    fun onShowFailed(message: String)

    /**
     * 显示当前网络不可用
     */
    fun onShowNoNet()
}
