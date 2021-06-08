package com.aexyn.generalutils.di

import android.content.Context
import com.aexyn.generalutils.api.HeaderInterceptor
import com.aexyn.generalutils.api.RetrofitBuilder.getRetrofit
import com.aexyn.generalutils.pref.ReadPref
import com.aexyn.generalutils.pref.SavePref
import com.aexyn.generalutils.utils.Constants.Companion.BASE_URL
import com.aexyn.generalutils.utils.Constants.Companion.deviceId
import com.aexyn.generalutils.utils.DispatcherProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppcontext(@ApplicationContext appContext: Context) = appContext

    @Singleton
    @Provides
    fun provideReadPref(appContext: Context): ReadPref = ReadPref(provideAppcontext(appContext))

    @Singleton
    @Provides
    fun provideSavePref(appContext: Context): SavePref = SavePref(appContext)

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideHeaderInterceptor(appContext: Context): Interceptor = HeaderInterceptor(provideReadPref(appContext), deviceId)

    @Singleton
    @Provides
    fun provideRetrofit(appContext: Context): Retrofit = getRetrofit(BASE_URL, provideHeaderInterceptor(appContext))

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}