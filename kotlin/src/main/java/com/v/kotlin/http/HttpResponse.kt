package com.v.kotlin.http

data class HttpResponse<T>(val data: T, val errorCode: Int, val errorMsg: String)