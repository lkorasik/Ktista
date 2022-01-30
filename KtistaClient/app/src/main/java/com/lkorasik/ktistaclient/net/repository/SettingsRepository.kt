package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.SettingsResponseDTO
import retrofit2.Response

class SettingsRepository {
    fun getSettings(): Response<SettingsResponseDTO?> {
        return RequestContext.API.getSettings(JwtRepository.jwt).execute()
    }
}