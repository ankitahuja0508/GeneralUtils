package com.aexyn.basicutilslibrary

import DevUtils
import android.app.Application

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        DevUtils.initFlipper(this)
    }
}