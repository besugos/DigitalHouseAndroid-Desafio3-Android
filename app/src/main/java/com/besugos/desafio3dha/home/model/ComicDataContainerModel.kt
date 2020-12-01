package com.besugos.desafio3dha.home.model

data class ComicDataContainerModel<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)