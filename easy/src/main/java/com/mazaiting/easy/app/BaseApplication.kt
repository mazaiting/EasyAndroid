package com.mazaiting.easy.app

import android.app.Application
import com.mazaiting.easy.base.component.DaggerApplicationComponentImpl
import com.mazaiting.easy.base.component.IApplicationComponent
import com.mazaiting.easy.base.module.ApplicationModule

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
    lateinit var baseConfig: BaseConfig

    /**
     * 获取Application组件
     * @return IApplicationComponent
     */
    lateinit var applicationComponent: IApplicationComponent

    override fun onCreate() {
        super.onCreate()
        // 初始化App
        instance = this
        // 初始化组件
        initApplicationComponent()
        // 初始化配置文件
        initConfig()
        // 初始化其他配置
        initOtherConfig()
    }

    /**
     * 初始化配置
     */
    private fun initConfig() {
        // 获取配置
        baseConfig = getConfig()
        // 初始化
        baseConfig.init(this)
    }

    /**
     * 获取配置工具
     * @return BaseConfig 子类
     */
    protected open fun getConfig(): BaseConfig = BaseConfig()

    /**
     * 初始化组件
     */
    private fun initApplicationComponent() {
        applicationComponent = DaggerApplicationComponentImpl
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    /**
     * 初始化其他配置，如果有需要则重写
     */
    protected open fun initOtherConfig() {}

}
