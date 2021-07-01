package com.aexyn.generalutils.utils

import com.aexyn.generalutils.base.BaseResponse

data class Waiting(var isWaiting:Boolean, val waitingText:String? = null)

data class ErrorResponse(var error:String? = ""):BaseResponse()