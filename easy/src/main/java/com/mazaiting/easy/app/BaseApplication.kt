package com.mazaiting.easy.app

import android.app.Application

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
    /**配置文件对象 */
    private var sConfig: BaseConfig? = null
    /**全局应用组件 */
    private var mApplicationComponent: IApplicationComponent? = null

    /**
     * 获取Application组件
     * @return IApplicationComponent
     */
    val applicationComponent: IApplicationComponent
        get() {
            if (null == mApplicationComponent) {
                throw RuntimeException("mApplicationComponent is null! Please override initApplicationComponent function!")
            }
            return mApplicationComponent as IApplicationComponent
        }

    override fun onCreate() {
        super.onCreate()
        // 初始化App
        instance = initApp()
        // 设置ApplicationComponent
        initApplicationComponent()
        // 初始化配置文件
        initConfig()
        // 初始化其他配置
        initOtherConfig()
    }

    /**
     * 设置ApplicationComponent
     */
    private fun initApplicationComponent() {}

    /**
     * 初始化App
     * @return 返回当前类对象
     */
    protected abstract fun initApp(): BaseApplication

    /**
     * 初始化配置文件
     */
    private fun initConfig() {
        sConfig = setConfig()
        if (null != sConfig) {
            // 初始化各类工具
            sConfig!!.init(this)
        }
    }

    /**
     * 初始化其他配置，如果有需要则重写
     */
    protected fun initOtherConfig() {}

    /**
     * 设置配置文件
     * @return BaseConfig子类对象
     */
    protected fun setConfig(): BaseConfig? {
        return null
    }

    companion object {
        /**当前类对象 */
        /**
         * 获取当前类对象
         */
        lateinit var instance: BaseApplication
            protected set
    }
}
