package com.mazaiting.easy.base.module

import android.content.Context
import com.mazaiting.easy.app.BaseApplication
import com.mazaiting.easy.config.BaseConfig
import com.mazaiting.sp.SpUtil
import dagger.Module
import dagger.Provides

/***
 *
 *
 *                                                    __----~~~~~~~~~~~------___
 *                                   .  .   ~~//====......          __--~ ~~
 *                   -.            \_|//     |||\\  ~~~~~~::::... /~
 *                ___-==_       _-~o~  \/    |||  \\            _/~~-
 *        __---~~~.==~||\=_    -_--~/_-~|-   |\\   \\        _/~
 *    _-~~     .=~    |  \\-_    '-~7  /-   /  ||    \      /
 *  .~       .~       |   \\ -_    /  /-   /   ||      \   /
 * /  ____  /         |     \\ ~-_/  /|- _/   .||       \ /
 * |~~    ~~|--~~~~--_ \     ~==-/   | \~--===~~        .\
 *          '         ~-|      /|    |-~\~~       __--~~
 *                      |-~~-_/ |    |   ~\_   _-~            /\
 *                           /  \     \__   \/~                \__
 *                       _--~ _/ | .-~~____--~-/                  ~~==.
 *                      ((->/~   '.|||' -_|    ~~-/ ,              . _||
 *                                 -_     ~\      ~~---l__i__i__i--~~_/
 *                                 _-~-__   ~)  \--______________--~~
 *                               //.-~~~-~_--~- |-------~~~~~~~~
 *                                      //.-~~~--\
 *                               神兽保佑
 *                              代码无BUG!
 * @author mazaiting
 * @date 2019-11-18
 * @description 全局应用模块
 */
/**
 * @param application 全局应用对象
 */
@Module
class ApplicationModule constructor(private val application: BaseApplication) {

    /**
     * SharedPreferences 工具类
     * @return SharedPreferences
     */
    @Provides
    internal fun providedSharedPreferences(): SpUtil {
        return SpUtil.instance
    }

    /**
     * 提供 Application
     * @return BaseApplication
     */
    @Provides
    internal fun providedApplication(): BaseApplication {
        return application
    }

    /**
     * 提供上下文
     * @return 上下文
     */
    @Provides
    internal fun providedContext(): Context {
        return application.applicationContext
    }

}
















