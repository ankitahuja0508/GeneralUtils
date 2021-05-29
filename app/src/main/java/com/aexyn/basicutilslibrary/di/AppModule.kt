package com.aexyn.basicutilslibrary.di


import android.content.Context
import com.aexyn.generalutils.pref.ReadPref
import com.aexyn.generalutils.pref.SavePref
import com.aexyn.generalutils.utils.DispatcherProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getAppcontext(@ApplicationContext appContext: Context) = appContext

    @Singleton
    @Provides
    fun getReadPref(appContext: Context): ReadPref = ReadPref(getAppcontext(appContext))

    @Singleton
    @Provides
    fun getSavePref(appContext: Context): SavePref = SavePref(appContext)

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

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