@file:Suppress("NOTHING_TO_INLINE", "unused", "CAST_NEVER_SUCCEEDS")

package com.mazaiting.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Service
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.fragment.app.Fragment
import com.mazaiting.common.util.Internals

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
 * @description 意图工具
 */
/**
 * 创建意图
 * @param T 泛型 Activity
 * @return 返回 Intent 对象
 */
inline fun <reified T : Context> Context.intent(): Intent = Intent(this, T::class.java)

/**
 * 开启新页面
 * @param T 开启类 reified可获取传入的类字节码
 * @param params 可变参数
 */
inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) =
    Internals.internalStartActivity<T>(this, params)

/**
 * 开启新页面
 * @param T 开启类 reified可获取传入的类字节码
 * @param requestCode 请求码
 * @param params 可变参数
 */
inline fun <reified T : Activity> Activity.startActivityForResult(
    requestCode: Int,
    params: Array<out Pair<String, Any?>>
) = Internals.internalStartActivityForResult<T>(this, requestCode, params)

/**
 * 开启新页面
 * @param T 开启类 reified可获取传入的类字节码
 * @param params 可变参数
 */
inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) =
    this.context?.let { Internals.internalStartActivity<T>(it, params) }

/**
 * 开启新页面
 * @param T 开启类 reified可获取传入的类字节码
 * @param requestCode 请求码
 * @param params 可变参数
 */
inline fun <reified T : Activity> Fragment.startActivityForResult(
    requestCode: Int,
    params: Array<out Pair<String, Any?>>
) = this.activity?.let { Internals.internalStartActivityForResult<T>(it, requestCode, params) }

/**
 * 开启服务
 * @param T 服务
 * @param params 参数
 * @return 返回 Intent 对象
 */
inline fun <reified T: Service> Context.startService(vararg params: Pair<String, Any?>) =
    Internals.internalStartService<T>(this, params)

/**
 * 开启服务
 * @param T 服务
 * @param params 参数
 * @return 返回 Intent 对象
 */
inline fun <reified T: Service> Fragment.startService(vararg params: Pair<String, Any?>) =
    this.context?.let { Internals.internalStartService<T>(it, params) }

/**
 * 创建意图
 * @param T 上下文
 * @param params 参数
 * @return 返回 Intent 对象
 */
inline fun <reified T: Context> Context.intentFor(vararg params: Pair<String, Any?>) =
    Internals.createIntent<T>(this, params)

/**
 * 创建意图
 * @param T 上下文
 * @param params 参数
 * @return 返回 Intent 对象
 */
inline fun <reified T: Context> Fragment.intentFor(vararg params: Pair<String, Any?>) =
    this.context?.let { Internals.createIntent<T>(it, params) }

/**
 * 清除任务栈
 * @return 返回 Intent 对象
 */
inline fun Intent.clearTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) }

/**
 * 清除栈顶
 * @return 返回 Intent 对象
 */
inline fun Intent.clearTop(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) }

/**
 * 新任务
 * @return 返回 Intent 对象
 */
inline fun Intent.newTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }

/**
 * 无动画
 * @return 返回 Intent 对象
 */
inline fun Intent.noAnimation(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION) }

/**
 * 保持栈顶
 * @return 返回 Intent 对象
 */
inline fun Intent.singleTop(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP) }

/**
 * 浏览器
 * @param url 地址
 * @param newTask 是否为新栈, 默认为 false
 * @return true: 打开; false: 未打开
 */
inline fun Fragment.browse(url: String, newTask: Boolean = false) = activity?.browse(url, newTask)

/**
 * 浏览器
 * @param url 地址
 * @param newTask 是否为新栈, 默认为 false
 * @return true: 打开; false: 未打开
 */
fun Context.browse(url: String, newTask: Boolean = false): Boolean {
    return try {
        // 创建意图
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        // 设置任务栈
        if (newTask) intent.newTask()
        // 开启
        startActivity(intent)
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

/**
 * 分享
 * @param text 文本
 * @param subject 主题
 * @param title 标题
 * @return true: 分享成功; false: 分享失败
 */
inline fun Fragment.share(text: String, subject: String = "", title: String = "") = activity?.share(text, subject, title)

/**
 * 分享
 * @param text 文本
 * @param subject 主题
 * @param title 标题
 * @return true: 分享成功; false: 分享失败
 */
fun Context.share(text: String, subject: String = "", title: String = ""): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_SEND)
        // 设置文本类型
        intent.type = "text/plain"
        // 设置主题
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        // 设置内容
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, title))
        true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        false
    }
}

/**
 * 发送邮件
 * @param email email 地址
 * @param subject 主题
 * @param text 内容
 * @return true: 成功; false: 失败
 */
inline fun Fragment.email(email: String, subject: String = "", text: String = "") = activity?.email(email, subject, text)

/**
 * 发送邮件
 * @param email email 地址
 * @param subject 主题
 * @param text 内容
 * @return true: 成功; false: 失败
 */
fun Context.email(email: String, subject: String = "", text: String = ""): Boolean {
    // 创建意图
    val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
    // 设置 email
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    // 设置主题
    if (subject.isNotEmpty()) intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    // 设置文本
    if (text.isNotEmpty()) intent.putExtra(Intent.EXTRA_TEXT, text)
    // 开启页面
    return if (null != intent.resolveActivity(packageManager)) {
        startActivity(intent)
        true
    } else {
        false
    }
}

/**
 * 打电话
 * @param number 电话号码
 * @return true: 成功; false: 失败
 */
@RequiresPermission(android.Manifest.permission.CALL_PHONE)
inline fun Fragment.makeCall(number: String) = activity?.makeCall(number)

/**
 * 打电话(需要{@Link #android.Manifest.permission.CALL_PHONE}权限)
 * @param number 电话号码
 * @return true: 成功; false: 失败
 */
@RequiresPermission(android.Manifest.permission.CALL_PHONE)
fun Context.makeCall(number: String): Boolean {
    return try {
        startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:$number")))
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

/**
 * 发送短信
 * @param number 手机号
 * @param text 短信内容
 * @return true: 发送成功; false: 发送失败
 */
inline fun Fragment.sendSMS(number: String, text: String = "") = activity?.sendSMS(number, text)

/**
 * 发送短信
 * @param number 手机号
 * @param text 短信内容
 * @return true: 发送成功; false: 发送失败
 */
fun Context.sendSMS(number: String, text: String = ""): Boolean {
    return try {
        // 创建意图
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:$number"))
        // 设置短信内容
        intent.putExtra("sms_body", text)
        // 开启
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}
