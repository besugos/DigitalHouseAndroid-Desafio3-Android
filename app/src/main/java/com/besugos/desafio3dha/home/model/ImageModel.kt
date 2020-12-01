package com.besugos.desafio3dha.home.model

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("path")
    val pathImagem: String,
    @SerializedName("extension")
    val extensaoImagem: String
) {
    fun getImagePath(imageResolution: String? = "detail"): String {
        return "$pathImagem/$imageResolution.$extensaoImagem".replace("http://", "https://")
    }
}

