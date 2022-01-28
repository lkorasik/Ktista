package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.ProfileRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.ProfileResponseDTO
import com.lkorasik.ktistaclient.net.repository.JwtRepository

class ProfileRequest: Request<ProfileResponseDTO>() {
    fun getProfile(user: ProfileRequestDTO) = RequestContext.API.getProfile(JwtRepository.jwt, user.id)?.enqueue(this)
}