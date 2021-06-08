package com.aexyn.basicutilslibrary

import DevUtils
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        DevUtils.initFlipper(this)
    }
}