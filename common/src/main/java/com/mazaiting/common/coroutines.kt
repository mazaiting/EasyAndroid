package com.mazaiting.common

import kotlinx.coroutines.*

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
 * @description 协程工具
 */

/**
 * 响应体
 * @param call 网络请求
 * @param response 响应
 * @param throwable 异常
 * @param T 泛型返回数据
 */
fun <T: Any> response(call: () -> Deferred<T>, response: (T) -> Unit, throwable: (Throwable) -> Unit){
    // 异常捕获
    val handler = CoroutineExceptionHandler { _, exception -> throwable.invoke(exception) }
    // 协程
    GlobalScope.launch(handler) {
        // 请求
        val data = request { call.invoke() }
        // 响应
        response.invoke(data)
        return@launch
    }
}

/**
 * 挂起 请求
 * @param call 网络请求
 * @param T 泛型返回
 */
suspend fun <T : Any> request(call: () -> Deferred<T>): T = withContext(Dispatchers.IO){ call.invoke().await() }
