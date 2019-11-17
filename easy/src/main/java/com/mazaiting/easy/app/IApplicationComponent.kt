package com.mazaiting.easy.app

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
}
