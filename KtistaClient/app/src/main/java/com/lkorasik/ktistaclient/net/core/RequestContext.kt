package com.lkorasik.ktistaclient.net.core

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestContext{
    private val cookieHandler: CookieHandler
    private val ktistaAPI: KtistaAPI
    private const val BASE_URL = "http://192.168.0.231:8080/"
    val API: KtistaAPI
        get() = ktistaAPI

    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        cookieHandler = CookieHandler()
        val client = OkHttpClient()
            .newBuilder()
            .cookieJar(cookieHandler)
            .build()
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        ktistaAPI = retrofit.create(KtistaAPI::class.java)
    }
}