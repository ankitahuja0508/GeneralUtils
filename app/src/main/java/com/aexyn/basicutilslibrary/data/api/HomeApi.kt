package com.aexyn.basicutilslibrary.data.api


import com.aexyn.generalutils.base.BaseResponse
import retrofit2.Response
import retrofit2.http.POST

interface HomeApi {

    @POST("login")
    suspend fun testApi(): Response<BaseResponse>

}
