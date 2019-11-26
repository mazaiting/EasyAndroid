package com.mazaiting.common.util

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.mazaiting.common.intent
import java.io.Serializable

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
 * @date 2019-11-26
 * @description 内部功能
 */
object Internals {

    /**
     * 开启
     * @param T 开启类 reified可获取传入的类字节码
     * @param context 上下文
     * @param params 可变参数
     */
    inline fun <reified T : Activity> internalStartActivity(
        context: Context,
        params: Array<out Pair<String, Any?>>
    ) = context.startActivity(createIntent<T>(context, params))

    /**
     * 开启新页面并返回值
     * @param T 开启类 reified可获取传入的类字节码
     * @param requestCode 请求码
     * @param params 可变参数
     */
    inline fun <reified T : Activity> internalStartActivityForResult(
        activity: Activity,
        requestCode: Int,
        params: Array<out Pair<String, Any?>>
    ) = activity.startActivityForResult(createIntent<T>(activity, params), requestCode)

    /**
     * 开启服务
     * @param T 服务
     * @param params 参数
     */
    inline fun <reified T : Service> internalStartService(
        context: Context,
        params: Array<out Pair<String, Any?>>
    ) = run {
        // 创建意图
        val intent = createIntent<T>(context, params)
        // 开启服务
        context.startService(intent)
        // 返回意图
        intent
    }

    /**
     * 开启服务
     * @param T 服务
     * @param context 上下文
     * @param params 参数
     */
    inline fun <reified T : Context> createIntent(
        context: Context,
        params: Array<out Pair<String, Any?>>
    ) = run {
        // 创建意图
        val intent = context.intent<T>()
        // 填参数
        if (params.isNotEmpty()) fillIntentArguments(intent, params)
        // 返回
        intent
    }

    /**
     * 填充参数
     */
    @Suppress("CAST_NEVER_SUCCEEDS")
    fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
        params.forEach {
            when (val value = it.second) {
                null -> intent.putExtra(it.first, null as Serializable)
                is Int -> intent.putExtra(it.first, value)
                is Long -> intent.putExtra(it.first, value)
                is CharSequence -> intent.putExtra(it.first, value)
                is String -> intent.putExtra(it.first, value)
                is Float -> intent.putExtra(it.first, value)
                is Double -> intent.putExtra(it.first, value)
                is Char -> intent.putExtra(it.first, value)
                is Short -> intent.putExtra(it.first, value)
                is Boolean -> intent.putExtra(it.first, value)
                is Serializable -> intent.putExtra(it.first, value)
                is Bundle -> intent.putExtra(it.first, value)
                is Parcelable -> intent.putExtra(it.first, value)
                is Array<*> -> when {
                    value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<String>() -> intent.putExtra(it.first, value)
                    value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
                    else -> throw RuntimeException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
                }
                is IntArray -> intent.putExtra(it.first, value)
                is LongArray -> intent.putExtra(it.first, value)
                is FloatArray -> intent.putExtra(it.first, value)
                is DoubleArray -> intent.putExtra(it.first, value)
                is CharArray -> intent.putExtra(it.first, value)
                is ShortArray -> intent.putExtra(it.first, value)
                is BooleanArray -> intent.putExtra(it.first, value)
                else -> throw RuntimeException("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            return@forEach
        }
    }
}