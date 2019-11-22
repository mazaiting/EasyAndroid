package com.mazaiting.easy.config

import android.app.Application
import com.mazaiting.common.LOG_DEBUG
import com.mazaiting.report.LocalReportHandler

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

open class BaseConfig {

    /**
     * 是否处于调试阶段
     * @return true, 调试；false, 发布版
     */
    protected open val isDebug: Boolean
        get() = false

    /**
     * 是否开启日志
     * @return true, 开启；false, 不开启
     */
    protected open val isUseLogger: Boolean
        get() = false

    /**
     * 获取基类 url
     * @return 网络基地址, 必须以'/'结尾
     */
    val baseUrl: String
        get() = "http://127.0.0.1:8080/"

    /**
     * 初始化调试参数
     * @param application 全局Application
     */
    fun init(application: Application) {
        // 初始化日志打印
        LOG_DEBUG = isUseLogger && isDebug
        // 异常捕获
        LocalReportHandler(application)
    }

}
