package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.JWTTempStorage
import com.lkorasik.ktistaclient.net.model.dto.ProfileRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.SettingsRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.SettingsResponseDTO

class SettingsRequest: Request<SettingsResponseDTO>() {
    fun getSettings(user: SettingsRequestDTO) = RequestContext.API.getSettings("Bearer ${JWTTempStorage.jwt}", user.id)?.enqueue(this)
}
