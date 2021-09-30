package com.v.kotlin.http

import com.google.gson.annotations.SerializedName

data class HttpResponse<T>(val data: T) {

    @SerializedName("errcode")
    var errorCode: Int = 0

    @SerializedName("errMsg")
    var errorMsg: String = ""
}