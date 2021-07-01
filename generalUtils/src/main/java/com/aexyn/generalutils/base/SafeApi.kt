package com.aexyn.generalutils.base

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.aexyn.generalutils.api.Result
import com.aexyn.generalutils.constants.Constants.Companion.NETWORK_ERROR
import com.aexyn.generalutils.utils.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.coroutineContext

//https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912

suspend fun <T> callApi(context:Context, error:String, apiCall: suspend () -> Response<T>): Result<T> {
    return try {

        val response = apiCall.invoke()
        val result = response.body()
        if(response.isSuccessful && result != null) {
            Result.Success(result)
        }else{
            val errorResponse: ErrorResponse? = convertErrorBody(response)
            if(response.code() == 401){
                Result.AuthenticationFailed(errorResponse?.message ?: errorResponse?.error ?: error)
            }else{
                Result.Error(errorResponse?.message ?: errorResponse?.error ?: error)
            }
        }
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> {
                context.sendBroadcast(Intent(NETWORK_ERROR))
                Result.NetworkError(throwable.localizedMessage ?: "Network error")
            }
            is HttpException -> {
                val code = throwable.code()
                val errorResponse = convertErrorBody(throwable.response())
                Result.Error(errorResponse?.message ?: errorResponse?.error ?: error)
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