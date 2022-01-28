package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.UserLoginRequestDTO
import retrofit2.Response

class UserRepository {
    fun login(user: UserLoginRequestDTO): Response<Void?> {
        return RequestContext.API.login(user).execute()
        //TODO(Handle errors. Response you can get from execute())
    }
}