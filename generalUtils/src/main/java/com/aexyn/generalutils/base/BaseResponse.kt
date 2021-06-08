package com.aexyn.generalutils.base

open class BaseResponse{

    open val message: String? = ""

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