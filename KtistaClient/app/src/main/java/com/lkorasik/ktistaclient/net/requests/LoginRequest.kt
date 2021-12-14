package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.model.UserLoginRequest
import com.lkorasik.ktistaclient.net.model.UserLoginResponse
import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext

class LoginRequest : Request<UserLoginResponse>() {
    fun loginUser(user: UserLoginRequest) = RequestContext.API.login(user)?.enqueue(this)
}