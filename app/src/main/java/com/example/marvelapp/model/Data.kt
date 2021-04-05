package com.example.marvelapp.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("")
    val count: Int,
    @SerializedName("")
    val limit: Int,
    @SerializedName("")
    val offset: Int,
    @SerializedName("")
    val results: List<Result>,
    @SerializedName("")
    val total: Int
)