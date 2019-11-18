package com.mazaiting.easy.base.presenter

import android.content.Context
import android.content.SharedPreferences
import com.mazaiting.easy.app.BaseApplication

import com.mazaiting.easy.base.mvp.IBaseView
import com.mazaiting.sp.SpUtil

import javax.inject.Inject


/**
 * 带SharedPreferences的Presenter
 * @author mazaiting
 */
open class BaseSpPresenter<T : IBaseView> : BasePresenter<T>() {
    /**
     * 注入 SharedPreferences 工具类
     */
    @Inject lateinit var sp: SpUtil
    /**
     * 注入上下文
     */
    @Inject lateinit var context: Context

    /**
     * 保存
     * @param key 键
     * @param value 值
     */
    protected fun put(key: String, value: Boolean) {
        sp.put(context, key, value)
    }

    /**
     * 获取对应键的值
     * @param key 键
     * @return Boolean数据
     */
    protected fun getBoolean(key: String): Boolean = sp.getBoolean(context, key)

    /**
     * 获取对应键的值
     * @param key 键
     * @return 字符串数据
     */
    protected fun getString(key: String): String? = sp.getString(context, key)

    /**
     * 获取对应键的值
     * @param key 键
     * @return int数据
     */
    protected fun getInt(key: String): Int = sp.getInt(context, key)
}
