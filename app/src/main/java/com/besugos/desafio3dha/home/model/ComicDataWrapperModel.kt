package com.besugos.desafio3dha.home.model

data class ComicDataWrapperModel<T>(
    val code: Int?,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    val attributionHTML: String?,
    val etag: String?,
    val data: ComicDataContainerModel<T>
)