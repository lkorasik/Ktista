package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.model.dto.UserLoginRequestDTO
import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext

class LoginRequest : Request<Void>() {
    fun loginUser(userDTO: UserLoginRequestDTO) = RequestContext.API.login(userDTO)?.enqueue(this)
}