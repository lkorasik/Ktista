package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.model.HeadersKeys
import retrofit2.Response

object JwtRepository {
    var jwt: String = ""
        private set
        get

    fun <T> extractToken(response: Response<T>) {
        jwt = response.headers()[HeadersKeys.AUTHORIZATION].toString().split(" ")[1]
    }
}