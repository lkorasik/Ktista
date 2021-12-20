package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.model.dto.UserRegistrationRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.UserRegistrationResponseDTO
import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext


class RegistrationRequest : Request<UserRegistrationResponseDTO>() {
    fun registerUser(userDTO: UserRegistrationRequestDTO) = RequestContext.API.register(userDTO)?.enqueue(this)
}

