package com.aexyn.generalutils.utils

data class Waiting(var isWaiting:Boolean, val waitingText:String? = null)

data class ErrorResponse(var error:String)