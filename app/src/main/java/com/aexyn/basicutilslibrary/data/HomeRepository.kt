package com.aexyn.basicutilslibrary.data


import com.aexyn.basicutilslibrary.data.api.HomeApi
import com.aexyn.generalutils.api.Result
import com.aexyn.generalutils.base.BaseResponse
import com.aexyn.generalutils.base.callApi
import javax.inject.Inject


class HomeRepository @Inject constructor(private val homeApi: HomeApi) {

    var resultList: ArrayList<String>? = null
        private set

    fun setResultList(resultList: ArrayList<String>?) {
        this.resultList = resultList
    }

    suspend fun getTestQuery(): Result<BaseResponse> {

        val response = callApi("An error occurred while fetching result list") {
            homeApi.testApi()
        }

        if(response is Result.Success){
            print(response.data)
        }

        return response

    }

}