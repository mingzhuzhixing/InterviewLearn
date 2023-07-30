package com.v.kotlin_app.http

import com.google.gson.annotations.SerializedName

data class HttpResponse<T>(val data: T) {

    @SerializedName(value = "errcode", alternate = ["errorCode"])
    var errorCode: Int = 0

    @SerializedName(value = "errMsg", alternate = ["errorMsg"])
    var errorMsg: String = ""
}