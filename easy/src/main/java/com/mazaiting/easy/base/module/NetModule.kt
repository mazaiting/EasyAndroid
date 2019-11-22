package com.mazaiting.easy.base.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mazaiting.easy.app.BaseApplication
import com.mazaiting.easy.utils.rx.RxUtil
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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
 * @description 网络模块
 */
@Module
class NetModule {
    /**
     * 获取Retrofit.Builder对象
     * @param application 全局Application对象
     * @return Retrofit
     */
    @Provides
    internal fun providedRetrofit(application: BaseApplication): Retrofit =
        Retrofit.Builder()
            // 网络基地址
            .baseUrl(application.baseConfig.baseUrl)
            // 添加Gson解析
            .addConverterFactory(GsonConverterFactory.create())
            // 协程适配器
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            // 添加RxJava适配器
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            // 设置OkHttpClient
            .client(RxUtil.getOkHttpClientWithCache(application))
            // 构建
            .build()

    /**
     * 提供 JSON 解析工具
     * @return Gson
     */
    @Provides
    internal fun providedGson(): Gson =
        GsonBuilder().disableHtmlEscaping().create()
}








