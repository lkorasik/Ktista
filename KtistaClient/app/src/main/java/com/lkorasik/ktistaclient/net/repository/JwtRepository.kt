package com.lkorasik.ktistaclient.net.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.lkorasik.ktistaclient.net.model.HeadersKeys
import retrofit2.Response

object JwtRepository {

    private var jwtPreferences: SharedPreferences? = null
    private const val JWT_PREFERENCES = "JWT_PREFERENCES"
    private const val JWT_ACCESS = "JWT_ACCESS"
    private const val JWT_REFRESH = "JWT_REFRESH"

    var jwtAccess: String = ""
        private set
        get() {
            if (field.isEmpty()) {
                field = getToken(JWT_ACCESS) ?: ""
            }
            return field
        }

    fun <T> saveToken(response: Response<T>, context: Context) {
        jwtAccess = extractToken(response)
        jwtPreferences = context.getSharedPreferences(JWT_PREFERENCES, Context.MODE_PRIVATE)

        Log.e(javaClass.name, jwtAccess)
        jwtPreferences?.let {
            with(it.edit()) {
                putString(JWT_ACCESS, jwtAccess)
                apply()
            }
        }
    }

    private fun getToken(nameToken: String): String? {
        return jwtPreferences?.getString(nameToken, "")
    }

    private fun <T> extractToken(response: Response<T>): String =
        response.headers()[HeadersKeys.AUTHORIZATION].toString().split(" ")[1]

}
