package com.lkorasik.ktistaclient.net.core

import com.lkorasik.ktistaclient.net.model.dto.ProfileResponseDTO
import com.lkorasik.ktistaclient.net.model.dto.UserLoginRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.UserRegistrationRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.UserRegistrationResponseDTO
import retrofit2.*
import retrofit2.http.*


interface KtistaAPI {
    @POST("api/auth/registration/")
    fun register(@Body userDTO: UserRegistrationRequestDTO): Call<UserRegistrationResponseDTO?>?

    @POST("api/auth/login")
    fun login(@Body userDTO: UserLoginRequestDTO): Call<Void?>?

    @GET("api/user/profile/{id}")
    fun getProfile(@Path("id") id: Long): Call<ProfileResponseDTO?>?
}