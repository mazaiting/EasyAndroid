package com.mazaiting.easy.utils

import android.annotation.SuppressLint
import android.util.Base64

import java.io.UnsupportedEncodingException
import java.security.Key
import java.security.NoSuchAlgorithmException
import java.security.NoSuchProviderException
import java.security.SecureRandom

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * AES加密工具类
 * 使用：
 * {
 * String source = "mazaiting";
 * String key = "123456";
 *
 *
 * String encryptString = AesUtil.encrypt(source, key);
 * System.out.println("加密后: " + encryptString);
 *
 *
 * String decryptString = AesUtil.decrypt(encryptString, key);
 * System.out.println("解密后: " + decryptString);
 * }
 *
 * @author mazaiting
 * @date 2018/3/30
 */

object AesUtil {
    /**
     * 算法Key
     */
    private const val KEY_ALGORITHM = "AES"
    /**
     * 加密算法
     */
    private const val CIPHER_ALGORITHM = "AES"


    /**
     * 加密数据
     *
     * @param data 待加密内容
     * @param key  加密的密钥
     * @return 加密后的数据
     */
    fun encrypt(data: String, key: String): String {
        return try {
            // 获得密钥
            val desKey = keyGenerator(key)
            // 实例化一个密码对象
            val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
            // 密码初始化
            cipher.init(Cipher.ENCRYPT_MODE, desKey)
            // 执行加密
            val bytes = cipher.doFinal(data.toByteArray(charset("UTF-8")))
            Base64.encodeToString(bytes, Base64.DEFAULT)
        } catch (e: Exception) {
            // 解析异常
            ""
        }

    }

    /**
     * 解密数据
     *
     * @param data 待解密的内容
     * @param key  解密的密钥
     * @return 解密后的字符串
     */
    fun decrypt(data: String, key: String): String {
        return try {
            // 生成密钥
            val kGen = keyGenerator(key)
            // 实例化密码对象
            val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
            // 初始化密码对象
            cipher.init(Cipher.DECRYPT_MODE, kGen)
            // 执行解密
            val bytes = cipher.doFinal(Base64.decode(data, Base64.DEFAULT))
            String(bytes)
        } catch (e: Exception) {
            e.printStackTrace()
            // 解析异常
            ""
        }

    }

    /**
     * 获取密钥
     *
     * @param key 密钥字符串
     * @return 返回一个密钥
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    @SuppressLint("DeletedProvider")
    @Throws(
        NoSuchAlgorithmException::class,
        UnsupportedEncodingException::class,
        NoSuchProviderException::class
    )
    private fun keyGenerator(key: String): Key {
        val kGen = KeyGenerator.getInstance(KEY_ALGORITHM)
        val secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto")
        secureRandom.setSeed(key.toByteArray())
        kGen.init(128, secureRandom)
        val secretKey = kGen.generateKey()
        val encoded = secretKey.encoded
        return SecretKeySpec(encoded, KEY_ALGORITHM)
    }
}
