package com.mazaiting.easy.base.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

/**
 * 网络改变广播接收器-- 基类
 * @author mazaiting
 */
abstract class BaseNetChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        /**
         * 这个监听网络连接的设置，包括Wifi和移动数据的打开和关闭
         * 最好还是用这个监听，Wifi如果打开，关闭以及链接上可用的连接都会接到监听
         * 这个广播的最大的弊端势必上边两个广播的反应都要慢
         */
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
            // 获取连接管理者
            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            // 获取网络状态信息
            val networkInfo = manager.activeNetworkInfo
            // 判断网络信息是否为空并且已连接
            if (null != networkInfo && networkInfo.isConnected) {
                if (ConnectivityManager.TYPE_WIFI == networkInfo.type) {
                    // 判断是否为Wifi
                    typeWifi(context)
                    //                    Log.e(TAG, "onReceive: 当前Wifi可用");
                    //                    Toast.makeText(context, "当前Wifi可用", Toast.LENGTH_SHORT).show();
                } else if (ConnectivityManager.TYPE_MOBILE == networkInfo.type) {
                    // 判断是否为移动网络
                    typeMobile(context)
                    //                    Log.e(TAG, "onReceive: 当前移动网络可用");
                    //                    Toast.makeText(context, "当前移动网络可用", Toast.LENGTH_SHORT).show();
                }
            } else {
                // 无网络
                typeNone(context)
                //                Log.e(TAG, "onReceive: 当前没有网络连接，请确保你已经打开网络!");
                //                Toast.makeText(context, "当前没有网络连接，请确保你已经打开网络!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 手机网络
     * @param context 上下文
     */
    protected fun typeMobile(context: Context) {

    }

    /**
     * 无线网络
     * @param context 上下文
     */
    protected fun typeWifi(context: Context) {

    }

    /**
     * 无网络
     * @param context 上下文
     */
    protected abstract fun typeNone(context: Context)

    companion object {
        /**无网络类型 */
        val TYPE_NONE = 0x01
        /**手机网络类型 */
        val TYPE_MOBILE = 0x02
        /**无线网络类型 */
        val TYPE_WIFI = 0x03
    }

}
