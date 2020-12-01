package com.besugos.desafio3dha.home.repository

import com.besugos.desafio3dha.data.api.NetworkUtils
import com.besugos.desafio3dha.home.model.ComicDataWrapperModel
import com.besugos.desafio3dha.home.model.ComicModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IMarvelEndpoint {
    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int? = 0,
        @Query("series") series: Int? = 17285
    ): ComicDataWrapperModel<ComicModel>

    companion object {
        val Endpoint: IMarvelEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IMarvelEndpoint::class.java)
        }
    }
}