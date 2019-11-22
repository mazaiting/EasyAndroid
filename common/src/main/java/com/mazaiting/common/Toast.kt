@file:Suppress("NOTHING_TO_INLINE", "unused")
package com.mazaiting.common

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

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
 * @description Toast 提示
 * inline 为了减少参数的入栈和出栈
 */

// ==================================Context 扩展======================================

/**
 * 显示 Toast
 * @param message 提示信息
 * @return 返回 Toast, 方便使用者修改相关属性
 */
inline fun Context.toast(message: CharSequence): Toast = Toast
        .makeText(this, message, Toast.LENGTH_SHORT)
        .apply { show() }

/**
 * 显示 Toast
 * @param resId 资源 ID
 * @return 返回 Toast, 方便使用者修改相关属性
 */
inline fun Context.toast(@StringRes resId: Int): Toast = toast(this.getString(resId))

/**
 * 显示长时 Toast
 * @param message 提示信息
 * @return 返回 Toast, 方便使用者修改相关属性
 */
inline fun Context.longToast(message: CharSequence): Toast = Toast
        .makeText(this, message, Toast.LENGTH_LONG)
        .apply { show() }

/**
 * 显示长时 Toast
 * @param resId 资源 ID
 * @return 返回 Toast, 方便使用者修改相关属性
 */
inline fun Context.longToast(@StringRes resId: Int): Toast = longToast(this.getString(resId))

// ==================================Fragment 扩展======================================

/**
 * 显示 Toast
 * @param message 提示信息
 * @return 返回 Toast, 方便使用者修改相关属性
 */
inline fun Fragment.toast(message: CharSequence): Toast = Toast
        .makeText(this.activity, message, Toast.LENGTH_SHORT)
        .apply { show() }

/**
 * 显示 Toast
 * @param resId 资源 ID
 * @return 返回 Toast, 方便使用者修改相关属性
 */
inline fun Fragment.toast(@StringRes resId: Int): Toast = toast(this.getString(resId))

/**
 * 显示长时 Toast
 * @param message 提示信息
 * @return 返回 Toast, 方便使用者修改相关属性
 */
inline fun Fragment.longToast(message: CharSequence): Toast = Toast
        .makeText(this.activity, message, Toast.LENGTH_LONG)
        .apply { show() }

/**
 * 显示长时 Toast
 * @param resId 资源 ID
 * @return 返回 Toast, 方便使用者修改相关属性
 */
inline fun Fragment.longToast(@StringRes resId: Int): Toast = longToast(this.getString(resId))