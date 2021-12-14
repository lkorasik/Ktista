package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.GetProfileRequest
import com.lkorasik.ktistaclient.net.model.ProfileResponse

class ProfileRequest: Request<ProfileResponse>() {
    fun getProfile(user: GetProfileRequest) = RequestContext.API.getProfile(user.id)?.enqueue(this)
}