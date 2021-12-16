package com.lkorasik.ktistaclient.net.core

import com.lkorasik.ktistaclient.net.model.*
import retrofit2.*
import retrofit2.http.*


interface KtistaAPI {
    @POST("api/user/registration/")
    fun register(@Body user: UserRegistrationRequest): Call<UserRegistrationResponse?>?

    @POST("api/user/login/")
    fun login(@Body user: UserLoginRequest): Call<UserLoginResponse?>?

    @GET("api/user/profile/{id}")
    fun getProfile(@Path("id") id: Long): Call<ProfileResponse?>?
}