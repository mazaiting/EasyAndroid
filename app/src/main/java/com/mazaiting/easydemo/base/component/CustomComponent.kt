package com.mazaiting.easydemo.base.component

import com.mazaiting.easy.base.component.ApplicationComponentImpl
import com.mazaiting.easy.base.component.INetComponent
import com.mazaiting.easy.base.module.NetModule
import com.mazaiting.easydemo.base.module.TestModule
import com.mazaiting.easydemo.base.service.TestApi
import com.mazaiting.easydemo.function.main.MainActivity
import dagger.Component

/**
 * 主页组件
 */
@Component(dependencies = [ApplicationComponentImpl::class],
    modules = [NetModule::class, TestModule::class])
interface CustomComponent: INetComponent {

    val testApi: TestApi
    /**
     * 注入主页
     */
    fun inject(activity: MainActivity)
}