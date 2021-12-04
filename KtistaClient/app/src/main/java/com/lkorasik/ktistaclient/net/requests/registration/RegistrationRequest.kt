package com.lkorasik.ktistaclient.net.requests.registration

import com.lkorasik.ktistaclient.net.model.UserRegistrationRequest
import com.lkorasik.ktistaclient.net.model.UserRegistrationResponse
import com.lkorasik.ktistaclient.net.requests.Request
import com.lkorasik.ktistaclient.net.requests.RequestContext


class RegistrationRequest : Request<UserRegistrationResponse>() {
    fun registerUser(user: UserRegistrationRequest) = RequestContext.API.register(user)?.enqueue(this)
}

