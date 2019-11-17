package com.mazaiting.easy.base.intercepter

import android.util.Log
import androidx.annotation.NonNull
import com.mazaiting.easy.config.Constant
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

/**
 * 添加统一参数
 */
class AppendParamInterceptor : Interceptor {

    @NonNull
    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        // 获取请求
        val request = chain.request()
        // 获取请求体
        val body = request.body

        //    Headers headers = request.headers();
        //    Log.e(Constant.TAG, headers.names().size()+"");
        //    for (String string: headers.names()) {
        //      Log.e(Constant.TAG, string);
        //    }

        if (null != body) {
            //      Log.e(Constant.TAG, body.contentLength()+"");
            //      Log.e(Constant.TAG, body.contentType().charset().toString());
            //      Log.e(Constant.TAG, body.contentType().type());
            val mediaType = body.contentType()
            // 编码集
            val charset = if (mediaType != null) mediaType.charset() else Charset.defaultCharset()
            // 检测是否为多文件上传
            val type = mediaType?.type ?: ""

            // 创建缓冲区
            val buffer = Buffer()
            // 将内容写入缓冲区
            body.writeTo(buffer)
            // 将缓冲区中的内容按指定字符读取出来
            val string = buffer.readString(Charset.defaultCharset())

            if (type == "multipart") {
                // 多文件上传
                //          MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", key);
                val token = MultipartBody.Part.createFormData("token", "xxx")
                //            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                //            MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), body);
                val builder = MultipartBody.Builder()
                //        for (File file : files) {
                //          RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
                //          builder.addFormDataPart("file", file.getName(), requestBody);
                //        }
                // 添加 token
                builder.addFormDataPart("token", "xxx")
                // 添加文件
                builder.addPart(body)
                // 设置为表单
                builder.setType(MultipartBody.FORM)
                val multipartBody = builder.build()
                return chain.proceed(request.newBuilder().post(multipartBody).build())
            } else {
                try {
                    // 创建 JSON
                    val jsonObject = JSONObject(string)
                    jsonObject.put("token", "xxxxxx")
                    Log.e(Constant.TAG, jsonObject.toString())
                    val requestBody =
                        RequestBody.create(body.contentType(), jsonObject.toString())
                    return chain.proceed(request.newBuilder().post(requestBody).build())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

            Log.e(Constant.TAG, string)
        }

        return chain.proceed(request)
    }
}
