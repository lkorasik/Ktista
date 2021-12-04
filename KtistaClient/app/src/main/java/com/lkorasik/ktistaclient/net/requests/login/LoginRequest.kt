package com.lkorasik.ktistaclient.net.requests.login

import android.util.Log
import com.lkorasik.ktistaclient.net.model.UserLoginRequest
import com.lkorasik.ktistaclient.net.requests.OnResultListener
import com.lkorasik.ktistaclient.net.requests.RequestContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRequest : Callback<String?> {
    private var listeners = mutableListOf<OnResultListener>()

    fun setOnResultListener(listener: OnResultListener) = listeners.add(listener)

    fun loginUser(user: UserLoginRequest) = RequestContext.API.login(user)?.enqueue(this)

    override fun onResponse(call: Call<String?>, response: Response<String?>) {
        if (response.isSuccessful) {
            for(listener in listeners)
                listener.onSuccess()
            Log.i("KtistaAppHttp", "Login success!!!")
        } else {
            for(listener in listeners)
                listener.onFail()
            Log.i("KtistaAppHttp", "Login fail!!!")
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<String?>, t: Throwable) {
        for(listener in listeners)
            listener.onFail()
        t.printStackTrace()
    }
}