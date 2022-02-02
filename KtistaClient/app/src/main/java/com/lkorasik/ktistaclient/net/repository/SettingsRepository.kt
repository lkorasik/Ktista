package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.SettingsDTO
import com.lkorasik.ktistaclient.ui.helper.converters.ConvertSettingsModel
import com.lkorasik.ktistaclient.ui.models.SettingsModel
import retrofit2.Response

class SettingsRepository {
    fun getSettings(): Response<SettingsResponseDTO?> {
        return RequestContext.API.getSettings(JwtRepository.jwtAccess).execute()
    }

    fun setSettings(settings: SettingsModel) {
        val settingsDTO = ConvertSettingsModel.convert(settings)

        RequestContext.API.setSettings(JwtRepository.jwt, settingsDTO).execute()
    }
}