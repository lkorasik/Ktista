package com.lkorasik.ktistaclient.net.core

import com.lkorasik.ktistaclient.net.model.*
import retrofit2.*
import retrofit2.http.*


interface KtistaAPI {
    @POST("api/auth/registration/")
    fun register(@Body user: UserRegistrationRequest): Call<UserRegistrationResponse?>?

    @POST("api/auth/login")
    fun login(@Body user: UserLoginRequest): Call<Void?>?

    @GET("api/user/profile/{id}")
    fun getProfile(@Path("id") id: Long): Call<ProfileResponse?>?
}