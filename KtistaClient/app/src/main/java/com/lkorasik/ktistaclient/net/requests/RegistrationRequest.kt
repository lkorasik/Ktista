package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.model.UserRegistrationRequest
import com.lkorasik.ktistaclient.net.model.UserRegistrationResponse
import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext


class RegistrationRequest : Request<UserRegistrationResponse>() {
    fun registerUser(user: UserRegistrationRequest) = RequestContext.API.register(user)?.enqueue(this)
}

