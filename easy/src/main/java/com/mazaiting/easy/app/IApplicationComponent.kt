package com.mazaiting.easy.app

import android.content.Context
import com.mazaiting.sp.SpUtil
import retrofit2.Retrofit

/**
 * 全局组件接口, 如要使用Dagger2框架，则需要实现此接口
 * @author mazaiting
 * @date 2018/2/6
 */

interface IApplicationComponent {

    /**
     * 获取全局Application对象
     * @return Application
     */
    val application: BaseApplication

    /**
     * 获取SharedPreferences
     * @return SharedPreferences
     */
    val sharedPreferences: SpUtil

    /**
     * 上下文
     * @return 上下文
     */
    val context: Context

    /**
     * Retrofit
     * @return 网络请求工具
     */
    val retrofit: Retrofit
}
