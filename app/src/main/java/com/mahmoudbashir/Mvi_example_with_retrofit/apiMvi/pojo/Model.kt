package com.mahmoudbashir.Mvi_example_with_retrofit.apiMvi.pojo

import com.google.gson.annotations.SerializedName

data class Model (
    @SerializedName("userId")
    val userId:Int?,
    @SerializedName("id")
    val id:Int?,
    @SerializedName("title")
    val title:String?,
    @SerializedName("body")
    val body:String?
    )