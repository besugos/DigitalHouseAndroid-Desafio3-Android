package com.besugos.desafio3dha.home.model

data class ComicModel (

    val id: Int,
    val title: String,
    val issueNumber: Double,
    val description: String,
    val pageCount: Int,
    val dates: List<DatesModel>,
    val prices: List<PricesModel>,
    val thumbnail: ThumbnailModel?
)