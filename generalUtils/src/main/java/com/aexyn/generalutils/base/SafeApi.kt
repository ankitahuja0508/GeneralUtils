package com.aexyn.generalutils.base

import com.aexyn.generalutils.api.Result
import com.aexyn.generalutils.utils.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

//https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912

suspend fun <T> callApi(error:String, apiCall: suspend () -> Response<T>): Result<T> {
    return try {

        val response = apiCall.invoke()
        val result = response.body()
        if(response.isSuccessful && result != null) {
            Result.Success(result)
        }else{
            val errorResponse: ErrorResponse? = convertErrorBody(response)
            if(response.code() == 401){
                Result.AuthenticationFailed(errorResponse?.error!!)
            }else{
                Result.Error(errorResponse?.error!!)
            }
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> Result.Error("Network Error") //Result.NetworkError
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable.response())
                Result.Error(errorResponse?.error!!)
            }
            else -> {
                Result.Error(throwable.localizedMessage ?: error)
            }
        }
    }
}

private fun convertErrorBody(response: Response<*>?): ErrorResponse? {
    return Gson().fromJson(response?.errorBody()!!.charStream(), ErrorResponse::class.java)
}