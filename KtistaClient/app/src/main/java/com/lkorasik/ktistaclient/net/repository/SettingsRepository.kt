package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.SettingsDTO
import retrofit2.Response

class SettingsRepository {
    fun getSettings(): Response<SettingsDTO?> {
        return RequestContext.API.getSettings(JwtRepository.jwt).execute()
    }

    fun setSettings(settings: SettingsDTO) {
        RequestContext.API.setSettings(JwtRepository.jwt, settings).execute()
    }
}