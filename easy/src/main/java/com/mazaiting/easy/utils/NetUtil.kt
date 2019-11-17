package com.mazaiting.easy.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

import java.net.InetAddress
import java.net.NetworkInterface
import java.util.Enumeration

/**
 * 网络工具类
 * @author mazaiting
 * @date 2018/3/29
 */

object NetUtil {

    /**
     * 获得本机ip地址
     *
     * @return ip地址
     */
    val hostIp: String?
        get() {
            try {
                val en = NetworkInterface
                    .getNetworkInterfaces()
                while (en.hasMoreElements()) {
                    val networkInterface = en.nextElement()
                    val ipAdd = networkInterface.inetAddresses
                    while (ipAdd
                            .hasMoreElements()
                    ) {
                        val inetAddress = ipAdd.nextElement()
                        if (!inetAddress.isLoopbackAddress) {
                            return inetAddress.hostAddress
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

    /**
     * 判断当前网络是否是移动网络
     *
     * @param context 上下文
     * @return boolean 3G
     */
    fun is3G(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * 判断当前网络是否是wifi网络
     *
     * @param context 上下文
     * @return true: wifi; false: 非wifi
     */
    fun isWifi(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * 判断当前网络是否是2G网络
     *
     * @param context 上下文
     * @return true: 2G; false: 不是2G
     */
    fun is2G(context: Context): Boolean {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfo = connectivityManager.activeNetworkInfo
        return if (null != activeNetInfo && (activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_EDGE
                    || activeNetInfo.subtype == TelephonyManager.NETWORK_TYPE_GPRS || activeNetInfo
                .subtype == TelephonyManager.NETWORK_TYPE_CDMA)
        ) {
            true
        } else false
    }

    /**
     * wifi是否打开
     * @param context 上下文
     * @return true: 打开; false: 未打开
     */
    fun isWifiEnabled(context: Context): Boolean {
        val mgrConn = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val mgrTel = context
            .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return mgrConn.activeNetworkInfo != null && mgrConn
            .activeNetworkInfo.state == NetworkInfo.State.CONNECTED || mgrTel
            .networkType == TelephonyManager.NETWORK_TYPE_UMTS
    }

    /**
     * 判断是否有网络连接
     *
     * @param context 上下文
     * @return 网络连接
     */
    fun isNetworkConnected(context: Context?): Boolean {
        if (null != context) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            val manager = context.getSystemService(
                Context
                    .CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            // 获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            //判断NetworkInfo对象是否为空
            if (null != networkInfo) {
                return networkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @param context 上下文
     * @return true, 可用; false，不可用
     */
    fun isMobileConnected(context: Context?): Boolean {
        if (context != null) {
            //获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            val manager = context.getSystemService(
                Context
                    .CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            //获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
            if (null != networkInfo && networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                return networkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 获取当前网络连接的类型信息
     * 原生
     *
     * @param context 上下文
     * @return 网络类型
     */
    fun getConnectedType(context: Context?): Int {
        if (null != context) {
            //获取手机所有连接管理对象
            val manager = context.getSystemService(
                Context
                    .CONNECTIVITY_SERVICE
            ) as ConnectivityManager
            //获取NetworkInfo对象
            val networkInfo = manager.activeNetworkInfo
            if (null != networkInfo && networkInfo.isAvailable) {
                //返回NetworkInfo的类型
                return networkInfo.type
            }
        }
        return -1
    }

    /**
     * 获取当前的网络状态 ：没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2
     * 自定义
     *
     * @param context 上下文
     * @return 网络类型
     */
    fun getApnType(context: Context): Int {
        //结果返回值
        var netType = 0
        //获取手机所有连接管理对象
        val manager = context.getSystemService(
            Context
                .CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        //获取NetworkInfo对象
        val networkInfo = manager.activeNetworkInfo ?: return netType
        //NetworkInfo对象为空 则代表没有网络
        //否则 NetworkInfo对象不为空 则获取该networkInfo的类型
        val nType = networkInfo.type
        if (nType == ConnectivityManager.TYPE_WIFI) {
            //WIFI
            netType = 1
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            // 获取子类型
            val nSubType = networkInfo.subtype
            val telephonyManager =
                context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (nSubType == TelephonyManager.NETWORK_TYPE_LTE && !telephonyManager.isNetworkRoaming) {
                // 4G
                netType = 4
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                || nSubType == TelephonyManager.NETWORK_TYPE_HSDPA
                || nSubType == TelephonyManager.NETWORK_TYPE_EVDO_0 && !telephonyManager.isNetworkRoaming
            ) {
                //3G   联通的3G为UMTS或HSDPA 电信的3G为EVDO
                netType = 3
            } else if (nSubType == TelephonyManager.NETWORK_TYPE_GPRS
                || nSubType == TelephonyManager.NETWORK_TYPE_EDGE
                || nSubType == TelephonyManager.NETWORK_TYPE_CDMA && !telephonyManager.isNetworkRoaming
            ) {
                //2G 移动和联通的2G为GPRS或EGDE，电信的2G为CDMA
                netType = 2
            } else {
                netType = 2
            }
        }
        return netType
    }
}
