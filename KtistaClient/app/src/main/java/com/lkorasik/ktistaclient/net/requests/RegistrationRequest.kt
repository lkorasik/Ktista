package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.model.dto.UserRegistrationRequestDTO
import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext


class RegistrationRequest : Request<Void>() {
    fun registerUser(userDTO: UserRegistrationRequestDTO) = RequestContext.API.register(userDTO).enqueue(this)
}

