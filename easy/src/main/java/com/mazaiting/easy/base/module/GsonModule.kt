package com.mazaiting.easy.base.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides

/**
 * JSON 解析模块
 */
@Module
class GsonModule {

    /**
     * 提供 JSON 解析工具
     * @return Gson
     */
    @Provides
    internal fun providedGson(): Gson =
        GsonBuilder()
            .disableHtmlEscaping()
            .create()
}