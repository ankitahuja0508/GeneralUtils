package com.aexyn.generalutils.base

import com.squareup.moshi.Json


open class BaseResponse{

    @Json(name = "message")
    open val message: String? = ""

    @Json(name = "status")
    open val status: Boolean = true

    @Json(name = "count")
    open val count: Int? = 0

    @Json(name = "pagination")
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