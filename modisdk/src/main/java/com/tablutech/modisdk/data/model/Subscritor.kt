package com.tablutech.modisdk.data.model

import com.google.gson.annotations.SerializedName

data class Subscritor(
    @SerializedName("data") val personalData: Data,
    val message: String,
    val status: Int,
    val success: Boolean
)