package com.besugos.desafio3dha.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val ts = System.currentTimeMillis().toString()
        val apikey = PUBLIC_KEY

        var request = chain.request()
        val originalHttpUrl = request.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(TS, ts)
            .addQueryParameter(API_KEY, apikey)
            .addQueryParameter(HASH, getHash(ts))
            .build()

        val requestBuilder = request.newBuilder().url(url)
        request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun getHash(ts: String) = "${ts}$PRIVATE_KEY$PUBLIC_KEY".md5

    private val String.md5: String
        get() {
            val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }

    companion object {
        private const val TS = "ts"
        private const val API_KEY = "apikey"
        private const val HASH = "hash"

        private const val PRIVATE_KEY = "0dd0c16fedb8a02985977eafca66b49f5e6a526f"
        const val PUBLIC_KEY = "6eb7e8896ec5850c52515a8a23ee97f0"

        fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
        }
    }
}



