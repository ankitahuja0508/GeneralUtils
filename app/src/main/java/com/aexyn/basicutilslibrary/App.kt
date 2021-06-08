package com.aexyn.basicutilslibrary

import DevUtils
import android.app.Application
import com.aexyn.generalutils.constants.Constants.Companion.BASE_URL
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        DevUtils.initFlipper(this)
        BASE_URL = "http://baggerdemo.newsoftdemo.info/api/v1/"
    }
}