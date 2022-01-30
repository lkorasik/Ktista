package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.HeadersKeys
import com.lkorasik.ktistaclient.net.model.dto.UserLoginRequestDTO
import okhttp3.Headers
import retrofit2.Response

class UserRepository {
    fun login(user: UserLoginRequestDTO): Response<Void?> {
        val response = RequestContext.API.login(user).execute()

        JwtRepository.extractToken(response)

        return response
        //TODO(Handle errors. Response you can get from execute())
    }
}

