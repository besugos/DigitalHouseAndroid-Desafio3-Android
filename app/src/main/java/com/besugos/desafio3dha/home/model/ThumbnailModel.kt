package com.besugos.desafio3dha.home.model

import com.google.gson.annotations.SerializedName

data class ThumbnailModel (
    @SerializedName("path")
    val pathThumb: String,
    @SerializedName("extension")
    val extensaoThumb: String
) {
    fun getImagePath(imageResolution: String? = "detail"): String {
        return "$pathThumb/$imageResolution.$extensaoThumb".replace("http://", "https://")
    }
}
