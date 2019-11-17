package com.mazaiting.easy.base.presenter

import android.content.SharedPreferences

import com.mazaiting.easy.base.mvp.IBaseView

import javax.inject.Inject


/**
 * 带SharedPreferences的Presenter
 * @author mazaiting
 */
class BaseSpPresenter<T : IBaseView> : BasePresenter<T>() {
    @Inject
    private val mSharedPreferences: SharedPreferences? = null
    /**
     * 存入Boolean值
     * @param key 键
     * @param value 值
     */
    protected fun putBoolean(key: String, value: Boolean) {
        mSharedPreferences!!.edit().putBoolean(key, value).apply()
    }

    /**
     * 存入String值
     * @param key 键
     * @param value 值
     */
    protected fun putString(key: String, value: String) {
        mSharedPreferences!!.edit().putString(key, value).apply()
    }

    /**
     * 存入int值
     * @param key 键
     * @param value 值
     */
    protected fun putInt(key: String, value: Int) {
        mSharedPreferences!!.edit().putInt(key, value).apply()
    }

    /**
     * 获取对应键的值
     * @param key 键
     * @return Boolean数据
     */
    protected fun getBoolean(key: String): Boolean {
        return mSharedPreferences!!.getBoolean(key, false)
    }

    /**
     * 获取对应键的值
     * @param key 键
     * @return Boolean数据
     */
    protected fun getString(key: String): String? {
        return mSharedPreferences!!.getString(key, "")
    }

    /**
     * 获取对应键的值
     * @param key 键
     * @return int数据
     */
    protected fun getInt(key: String): Int {
        return mSharedPreferences!!.getInt(key, 0)
    }
}
