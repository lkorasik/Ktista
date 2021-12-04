package com.lkorasik.ktistaclient.net.requests.login

import com.lkorasik.ktistaclient.net.model.UserLoginRequest
import com.lkorasik.ktistaclient.net.requests.Request
import com.lkorasik.ktistaclient.net.requests.RequestContext

class LoginRequest : Request<String>() {
    fun loginUser(user: UserLoginRequest) = RequestContext.API.login(user)?.enqueue(this)
}