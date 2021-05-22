package com.aexyn.generalutils.api

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<T>(val data: T?, val message: String?) {

    class Success<T>(data: T) : Result<T>(data, null)
    class AuthenticationFailed<T>(val errorString: String) : Result<T>(null, errorString)
    class Error<T>(message: String) : Result<T>(null, message)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is AuthenticationFailed -> "Failure=$errorString]"
            is Error -> "Error[exception=$message]"
        }
    }
}