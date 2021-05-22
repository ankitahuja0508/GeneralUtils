package com.aexyn.generalutils.di


import android.content.Context
import android.provider.Settings
import com.aexyn.generalutils.api.HeaderInterceptor
import com.aexyn.generalutils.api.RetrofitBuilder
import com.aexyn.generalutils.constants.Constants
import com.aexyn.generalutils.pref.ReadPref
import com.aexyn.generalutils.pref.SavePref
import com.aexyn.generalutils.utils.DispatcherProvider
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
    fun getAppcontext(@ApplicationContext appContext: Context) = appContext

    @Singleton
    @Provides
    fun getDeviceId(appContext: Context): String = Settings.Secure.getString(
        appContext.contentResolver,
        Settings.Secure.ANDROID_ID
    )

    @Singleton
    @Provides
    fun getReadPref(appContext: Context): ReadPref = ReadPref(getAppcontext(appContext))

    @Singleton
    @Provides
    fun getSavePref(appContext: Context): SavePref = SavePref(appContext)

    @Singleton
    @Provides
    fun provideBaseUrl(): String = Constants.BASE_URL + Constants.API_VERSION

    @Singleton
    @Provides
    fun provideHeaderInterceptor(appContext: Context): Interceptor = HeaderInterceptor(getReadPref(appContext), getDeviceId(appContext))

    @Singleton
    @Provides
    fun provideRetrofit(appContext: Context): Retrofit =
        RetrofitBuilder.getRetrofit(provideBaseUrl(), provideHeaderInterceptor(appContext))

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