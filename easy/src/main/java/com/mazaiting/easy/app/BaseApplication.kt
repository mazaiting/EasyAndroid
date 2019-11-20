package com.mazaiting.easy.app

import android.app.Application
import com.mazaiting.easy.base.component.ApplicationComponentImpl
import com.mazaiting.easy.base.component.DaggerApplicationComponentImpl
import com.mazaiting.easy.base.module.ApplicationModule
import com.mazaiting.easy.base.module.NetModule

import com.mazaiting.easy.config.BaseConfig

/**
 * Application基类，使用时只需继承此类即可
 * 1. 继承此类后如果需要进行需要对配置文件进行配置，则必须重写setConfig()方法，返回BaseConfig的子类
 * 2. 继承此类后必须实现initApp()方法，返回this即可。
 * 3. 如果要使用Dagger2框架，则需要重写initApplicationComponent()方法
 * @author mazaiting
 * @date 2018/2/5
 */
abstract class BaseApplication : Application() {
    companion object {
        /**
         * 获取当前类对象
         */
        lateinit var instance: BaseApplication
            private set
    }

    /** 配置文件对象 */
    open val config: BaseConfig?
        get() = null
    /**
     * 获取Application组件
     * @return IApplicationComponent
     */
    open val applicationComponent: IApplicationComponent
        get() = DaggerApplicationComponentImpl
            .builder()
            .applicationModule(ApplicationModule(this))
            .netModule(NetModule())
            .build()

    override fun onCreate() {
        super.onCreate()
        // 初始化App
        instance = this
        // 初始化配置文件
        config?.init(this)
        // 初始化其他配置
        initOtherConfig()
    }

    /**
     * 初始化其他配置，如果有需要则重写
     */
    protected open fun initOtherConfig() {}

}
