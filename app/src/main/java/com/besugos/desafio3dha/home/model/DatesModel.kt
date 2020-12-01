package com.besugos.desafio3dha.home.model

import com.google.gson.annotations.SerializedName

data class DatesModel(
    @SerializedName("type")
    val tipo: String,
    @SerializedName("date")
    val data: String
)