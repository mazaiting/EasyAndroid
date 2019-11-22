@file:Suppress("unused", "NOTHING_TO_INLINE")

package com.mazaiting.common

import android.util.Log

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
 * @date 2019-11-22
 * @description Logger 工具
 */

/**
 * 获取类名
 * @return 类名
 */
inline fun Any.getSimpleName(): String = this.javaClass.simpleName

/**
 * 标记是否打印日志
 * true: 打印
 * false: 不打印
 */
var DEBUG: Boolean = false

/**
 * VERBOSE 日志打印
 * @param message 打印字符串
 * @param thr 异常
 */
fun Any.verbose(message: Any?, thr: Throwable? = null) {
    log(this, message, thr,
        { tag, msg -> Log.v(tag, msg) },
        { tag, msg, _ -> Log.v(tag, msg, thr) })
}

/**
 * DEBUG 日志打印
 * @param message 打印字符串
 * @param thr 异常
 */
fun Any.debug(message: Any?, thr: Throwable? = null) {
    log(this, message, thr,
        { tag, msg -> Log.d(tag, msg) },
        { tag, msg, _ -> Log.d(tag, msg, thr) })
}

/**
 * INFO 日志打印
 * @param message 打印字符串
 * @param thr 异常
 */
fun Any.info(message: Any?, thr: Throwable? = null) {
    log(this, message, thr,
        { tag, msg -> Log.i(tag, msg) },
        { tag, msg, _ -> Log.i(tag, msg, thr) })
}

/**
 * WARN 日志打印
 * @param message 打印字符串
 * @param thr 异常
 */
fun Any.warn(message: Any?, thr: Throwable? = null) {
    log(this, message, thr,
        { tag, msg -> Log.w(tag, msg) },
        { tag, msg, _ -> Log.w(tag, msg, thr) })
}

/**
 * ERROR 日志打印
 * @param message 打印字符串
 * @param thr 异常
 */
fun Any.error(message: Any?, thr: Throwable? = null) {
    log(this, message, thr,
        { tag, msg -> Log.e(tag, msg) },
        { tag, msg, _ -> Log.e(tag, msg, thr) })
}

/**
 * ERROR 日志打印
 * @param message 打印字符串
 * @param thr 异常
 */
fun Any.wtf(message: Any?, thr: Throwable? = null) {
    if (thr != null) {
        Log.wtf(getSimpleName(), message?.toString() ?: "null", thr)
    } else {
        Log.wtf(getSimpleName(), message?.toString() ?: "null")
    }
}

/**
 * 打印日志
 * @param any 当前类对象
 * @param message 消息内容
 * @param thr 异常信息
 * @param f Log.d(TAG, "");
 * @param ft Log.d(TAG, "", thr);
 */
private inline fun log(
    any: Any,
    message: Any?,
    thr: Throwable?,
    f: (String, String) -> Unit,
    ft: (String, String, Throwable) -> Unit
) {
    // 获取 Tag
    val tag = any.getSimpleName()
    // 是否打印
    if (DEBUG) {
        // 如果不为空
        if (null != thr) {
            // 使用异常打印
            ft(tag, message?.toString() ?: "null", thr)
        } else {
            // 使用普通打印
            f(tag, message?.toString() ?: "null")
        }
    }
}