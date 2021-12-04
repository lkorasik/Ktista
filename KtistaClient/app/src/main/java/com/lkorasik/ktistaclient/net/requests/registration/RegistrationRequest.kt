package com.lkorasik.ktistaclient.net.requests.registration

import android.util.Log
import com.lkorasik.ktistaclient.net.model.*
import com.lkorasik.ktistaclient.net.requests.OnResultListener
import com.lkorasik.ktistaclient.net.requests.RequestContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationRequest : Callback<UserRegistrationResponse?> {
    private var listeners = mutableListOf<OnResultListener>()

    fun setOnResultListener(listener: OnResultListener) = listeners.add(listener)

    fun registerUser(user: UserRegistrationRequest) = RequestContext.API.register(user)?.enqueue(this)

    override fun onResponse(call: Call<UserRegistrationResponse?>, response: Response<UserRegistrationResponse?>) {
        if (response.isSuccessful) {
            //val user = response.body()!!
            //val headers = response.headers()
            //val cookie = headers.get("Set-Cookie")
            //val result = HttpCookie.parse(cookie.toString())
            //val jwt = result.first { it.name == "jwt" }.value
            for(listener in listeners)
                listener.onSuccess()
            Log.i("KtistaAppHttp", "Registration success!!!")
        } else {
            for(listener in listeners)
                listener.onFail()
            Log.i("KtistaAppHttp", "Registration fail!!!")
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<UserRegistrationResponse?>, t: Throwable) {
        for(listener in listeners)
            listener.onFail()
        t.printStackTrace()
    }
}