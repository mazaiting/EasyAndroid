package com.mazaiting.easydemo.module.main

import com.mazaiting.easy.base.component.ApplicationComponentImpl
import dagger.Component

/**
 * 主页组件
 */
@Component(dependencies = [ApplicationComponentImpl::class])
interface MainComponent {
    /**
     * 注入主页
     */
    fun inject(activity: MainActivity)
}