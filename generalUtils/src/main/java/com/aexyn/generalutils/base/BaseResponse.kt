package com.aexyn.generalutils.base

import com.google.gson.annotations.SerializedName

open class BaseResponse{

    open val message: String? = ""

    @SerializedName("status")
    open val status: Boolean = true

    open val count: Int? = 0

    val pagination: Pagination? = null

}

data class Pagination (
    val prev: Prev?,
    val next: Next?
)

data class Prev (
        val page: Int,
        val limit: Int
)

data class Next (
        val page: Int,
        val limit: Int
)