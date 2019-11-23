package com.mazaiting.easy.base.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mazaiting.easy.app.BaseApplication
import com.mazaiting.easy.utils.rx.RxUtil
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 协程模块
 */
@Module(includes = [GsonModule::class])
class CoroutineModule {

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
            // 设置OkHttpClient
            .client(RxUtil.getOkHttpClientWithCache(application))
            // 构建
            .build()
}