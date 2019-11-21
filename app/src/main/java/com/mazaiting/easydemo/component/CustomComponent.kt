package com.mazaiting.easydemo.component

import com.mazaiting.easy.base.component.ApplicationComponentImpl
import com.mazaiting.easy.base.component.INetComponent
import com.mazaiting.easy.base.module.NetModule
import com.mazaiting.easydemo.function.main.MainActivity
import dagger.Component

/**
 * 主页组件
 */
@Component(dependencies = [ApplicationComponentImpl::class],
    modules = [NetModule::class])
interface CustomComponent: INetComponent {
    /**
     * 注入主页
     */
    fun inject(activity: MainActivity)
}