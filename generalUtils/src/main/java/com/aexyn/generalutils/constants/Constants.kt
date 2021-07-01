package com.aexyn.generalutils.constants

class CustomHeaders(var key:String,var value:String)

class Constants {
    companion object{
        var BASE_URL :String = ""
        var deviceId = ""
        val SELECTED_RESELETED_ITEM = "SELECTED_RESELETED_ITEM"
        val NETWORK_ERROR = "NETWORK_ERROR"
        var CUSTOM_HEADERS = arrayListOf<CustomHeaders>()
    }

}