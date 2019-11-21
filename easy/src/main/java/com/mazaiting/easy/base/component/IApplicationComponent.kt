package com.mazaiting.easy.base.component

import android.content.Context
import com.mazaiting.easy.app.BaseApplication
import com.mazaiting.sp.SpUtil

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
}
