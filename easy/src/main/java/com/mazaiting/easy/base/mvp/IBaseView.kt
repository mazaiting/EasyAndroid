package com.mazaiting.easy.base.mvp

import java.io.Serializable

/**
 * MVP 中的 View 接口
 * @author mazaiting
 * @date 2018/2/5
 */

interface IBaseView: Serializable {

    /**
     * 请求成功
     */
    fun onShowSuccess()

    /**
     * 显示请求失败
     * @param message 错误信息
     */
    fun onShowFailed(message: String)
}
