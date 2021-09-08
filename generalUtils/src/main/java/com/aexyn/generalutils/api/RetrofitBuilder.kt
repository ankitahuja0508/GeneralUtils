package com.aexyn.generalutils.api

import com.aexyn.generalutils.constants.Constants.Companion.CUSTOM_HEADERS
import com.aexyn.generalutils.utils.DevUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    fun getRetrofit(baseUrl:String, interceptor: Interceptor): Retrofit {

        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)

        okHttpClientBuilder.addInterceptor(interceptor)

        DevUtils.attachNetworkInterceptor(okHttpClientBuilder)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }
}

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        val requestBuilder = request.newBuilder()

        /* requestBuilder
             .addHeader("content-type", "application/json")
             .addHeader("X-OS-TYPE", "android")
             .addHeader("X-DEVICE-ID", deviceId)
             .addHeader(
                 "X-OS-VERSION",
                 Build.VERSION.SDK_INT.toString() + " (" + Build.VERSION_CODES::class.java.fields[Build.VERSION.SDK_INT].name + ")"
             )
             .addHeader(
                 "X-OS-DEVICE",
                 Build.MODEL + " (" + Build.PRODUCT + ")"
             )

         if(request.header("No-Authentication")==null){
             val token = readPref.getStringValue("token")

             val authHeaderKey = "Authorization"

             if(!token.isNullOrEmpty()) {
                 val finalToken = "Bearer $token"
                 requestBuilder.addHeader(authHeaderKey, finalToken)
             }
         }*/

        requestBuilder.addHeader("content-type", "application/json")

        CUSTOM_HEADERS.forEach {
            requestBuilder.addHeader(it.key, it.value)
        }

        request = requestBuilder.build()

        return chain.proceed(request)
    }
}