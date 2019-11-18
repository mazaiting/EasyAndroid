package com.mazaiting.easy.utils.rx

import android.text.TextUtils
import android.util.Log
import androidx.annotation.NonNull
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.mazaiting.easy.app.BaseApplication
import com.mazaiting.easy.config.Constant
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * 网络工具类
 * @author mazaiting
 * @date 2018/2/6
 */
object RxUtil {

    /**
     * 获取日志拦截器
     * @return Http日志拦截器
     */
    private val httpLoggingInterceptor: HttpLoggingInterceptor
        get() {
            val level = HttpLoggingInterceptor.Level.BODY
            // 创建拦截器
            val loggingInterceptor =
                HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(@NonNull message: String) {
                        if (!TextUtils.isEmpty(message)) {
                            Log.d(Constant.TAG, message)
                        }
                    }
                })
            // 设置日志等级
            loggingInterceptor.level = level
            return loggingInterceptor
        }

    /**
     * 提供一个带缓存的OkHttpClient
     * @param application 全局Application
     * @return OkHttpClient
     */
    fun getOkHttpClientWithCache(application: BaseApplication): OkHttpClient {
        val builder = getOkHttpClientBuilder(application)
        // 设置缓存
        builder.cache(getOkHttpCache(application))
        return builder.build()
    }

    /**
     * 提供一个不带缓存的OkHttpClient
     * @param application 全局Application
     * @return OkHttpClient
     */
    fun getOkHttpClient(application: BaseApplication): OkHttpClient {
        val builder = getOkHttpClientBuilder(application)
        return builder.build()
    }

    /**
     * 获取
     * @param application 全局对象
     * @return OkHttpClient.Builder
     */
    private fun getOkHttpClientBuilder(application: BaseApplication): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            // 设置网络请求拦截器
            .addInterceptor(httpLoggingInterceptor)
            //                .addInterceptor(new AppendParamInterceptor())
            // 设置连接超时时间
            .connectTimeout(15, TimeUnit.SECONDS)
            // 设置读取超时时间
            .readTimeout(15, TimeUnit.SECONDS)
            // 设置持久化Cookie
            .cookieJar(
                PersistentCookieJar(
                    SetCookieCache(),
                    SharedPrefsCookiePersistor(application)
                )
            )
    }

    /**
     * 提供一个缓存对象
     * @param application 全局对象
     * @return 缓存对象
     */
    private fun getOkHttpCache(application: BaseApplication): Cache {
        // 可缓存的大小
        val cacheSize : Long = 20 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize)
    }
}
