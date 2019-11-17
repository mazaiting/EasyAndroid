package com.mazaiting.easy.config

import android.app.Application
import com.mazaiting.crash.CrashHandler
import com.mazaiting.log.L
import com.mazaiting.util.CrashLogUtil

/**
 * 配置文件基类
 * @author mazaiting
 * @date 2018/2/5
 *
 * 官方标准字体
 * style="@style/TextAppearance.AppCompat.Display4"
 * style="@style/TextAppearance.AppCompat.Display3"
 * style="@style/TextAppearance.AppCompat.Display2"
 * style="@style/TextAppearance.AppCompat.Display1"
 * style="@style/TextAppearance.AppCompat.Headline"
 * style="@style/TextAppearance.AppCompat.Title"
 * style="@style/TextAppearance.AppCompat.Subhead"
 * style="@style/TextAppearance.AppCompat.Body2"
 * style="@style/TextAppearance.AppCompat.Body1"
 * style="@style/TextAppearance.AppCompat.Caption"
 * style="@style/TextAppearance.AppCompat.Button"
 *
 * Button的Material Design设计
 * style="@style/Widget.AppCompat.Button"
 * style="@style/Widget.AppCompat.Button.Borderless"
 * style="@style/Widget.AppCompat.Button.Borderless.Colored"
 * style="@style/Widget.AppCompat.Button.Small"
 */

abstract class BaseConfig {

    /**
     * 是否处于调试阶段
     * @return true, 调试；false, 发布版
     */
    protected val isDebug: Boolean
        get() = false

    /**
     * 是否开启日志
     * @return true, 开启；false, 不开启
     */
    protected val isUseLogger: Boolean
        get() = false

    /**
     * 初始化调试参数
     * @param application 全局Application
     */
    fun init(application: Application) {
        // 初始化日志打印
        initLogger()
        // 异常捕获
        CrashHandler.getInstance()
    }

    /**
     * 初始化日志打印
     */
    private fun initLogger() {
        if (isUseLogger && isDebug) {
            //            Logger.addLogAdapter(new AndroidLogAdapter());
            L.setProp(isDebug, Constant.TAG)
        }
    }

}
