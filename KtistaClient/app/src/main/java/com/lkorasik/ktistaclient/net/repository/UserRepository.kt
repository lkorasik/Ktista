package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.UserLoginRequestDTO

class UserRepository {
    fun login(user: UserLoginRequestDTO): Boolean {
        val response = RequestContext.API.login(user)?.execute()

        //TODO(Handle errors. Response you can get from execute())

        response?.apply {
            return isSuccessful
        }

        return false
    }
}