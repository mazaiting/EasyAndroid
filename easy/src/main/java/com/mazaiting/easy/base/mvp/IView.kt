package com.mazaiting.easy.base.mvp

import android.os.Bundle
import android.view.View

import com.mazaiting.easy.base.component.IApplicationComponent

/**
 * View 接口， 用于Activity和Fragment中
 * @author mazaiting
 * @date 2018/2/5
 */

interface IView {

    /**
     * 获取布局
     * @return 布局
     */
    var rootView: View?

    /**
     * 获取布局ID
     * @return 布局ID
     */
    val contentLayout: Int

    /**
     * 注入ApplicationComponent, 若项目中未使用Dagger2框架，此方法中可不填写任何内容
     * @param applicationComponent Application组件
     */
    fun inject(applicationComponent: IApplicationComponent)

    /**
     * 绑定布局
     * @param view 视图
     * @param savedInstanceState bundle
     */
    fun bindView(view: View, savedInstanceState: Bundle?)

    /**
     * 初始化数据
     */
    fun initData()
}
