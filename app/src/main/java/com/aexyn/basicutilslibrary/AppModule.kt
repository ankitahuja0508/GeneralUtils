package com.aexyn.basicutilslibrary

import android.content.Context
import com.aexyn.basicutilslibrary.data.api.HomeApi
import com.aexyn.generalutils.di.AppModule.provideRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHomeApi(appContext: Context): HomeApi = provideRetrofit().create(HomeApi::class.java)

}