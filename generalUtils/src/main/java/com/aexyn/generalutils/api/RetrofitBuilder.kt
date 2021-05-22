package com.aexyn.generalutils.api
import DevUtils
import android.os.Build
import com.aexyn.generalutils.pref.ReadPref
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    fun getRetrofit(baseUrl:String, interceptor: Interceptor): Retrofit {

        val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)

        okHttpClientBuilder.addInterceptor(interceptor)

        DevUtils.attachNetworkInterceptor(okHttpClientBuilder)

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()!!))
                .client(okHttpClientBuilder.build())
                .build()
    }
}

class HeaderInterceptor(val readPref: ReadPref, val deviceId:String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        val requestBuilder = request.newBuilder()

        requestBuilder
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
                .addHeader("apptype", "admin")

        if(request.header("No-Authentication")==null){
            val token = readPref.getStringValue("token")

            val authHeaderKey = "Authorization"

            if(!token.isNullOrEmpty()) {
                val finalToken = "Bearer $token"
                requestBuilder.addHeader(authHeaderKey, finalToken)
            }
        }

        request = requestBuilder.build()

        return chain.proceed(request)
    }
}