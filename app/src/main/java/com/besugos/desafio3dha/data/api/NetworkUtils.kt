package com.besugos.desafio3dha.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest

class NetworkUtils {

    val timeStamp = System.currentTimeMillis().toString()

    fun generateHash(timeStamp: String, publicKey: String, privateKey: String): String {

        val initialValue = timeStamp + privateKey + publicKey
        val md5Encoder = MessageDigest.getInstance("MD5")
        val encoderDigest = md5Encoder.digest(initialValue.toByteArray())
        return encoderDigest.fold("", { str, it -> str + "%02x".format(it) })

    }

    fun currentTimeMilliSeconds(): Long {
        return System.currentTimeMillis()
    }

    companion object {
        private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

        fun getRetrofitInstance(baseUrl: String = BASE_URL): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}