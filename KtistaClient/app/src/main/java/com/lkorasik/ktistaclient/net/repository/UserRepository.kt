package com.lkorasik.ktistaclient.net.repository

import android.content.Context
import android.util.Log
import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.UserLoginRequestDTO
import retrofit2.Response

class UserRepository(private val context: Context) {
    fun login(user: UserLoginRequestDTO): Response<Void?> {
        val response = RequestContext.API.login(user).execute()

        if (response.isSuccessful) {
            JwtRepository.saveToken(response, context)
        }
        else {
            Log.e(javaClass.name,"Login Fail: $response.code()")
        }
        return response
        //TODO(Handle errors. Response you can get from execute())
    }
}

