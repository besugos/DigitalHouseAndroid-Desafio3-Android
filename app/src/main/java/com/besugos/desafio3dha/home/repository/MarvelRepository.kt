package com.besugos.desafio3dha.home.repository

class MarvelRepository {
    private val client = IMarvelEndpoint.Endpoint

    suspend fun getComics(offset: Int? = 0) = client.getComics(offset)
//    suspend fun getComics(title: String = "SPIDER") = client.getComics(title)
}