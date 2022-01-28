package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.ProfileResponseDTO
import retrofit2.Response

class ProfileRepository {
    fun getProfile(userId: Long): Response<ProfileResponseDTO?> {
        return RequestContext.API.getProfile(JwtRepository.jwt, userId).execute()
    }
}