package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.JWTTempStorage
import com.lkorasik.ktistaclient.net.model.dto.ProfileRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.ProfileResponseDTO

class ProfileRequest: Request<ProfileResponseDTO>() {
    fun getProfile(user: ProfileRequestDTO) = RequestContext.API.getProfile(JWTTempStorage.jwt, user.id)?.enqueue(this)
}