package com.lkorasik.ktistaclient.net

import android.util.Log
import com.google.gson.GsonBuilder
import com.lkorasik.ktistaclient.net.model.UserLoginRequest
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginRequest : Callback<String?> {
    private lateinit var cookieHandler: CookieHandler
    private var listeners = mutableListOf<OnResultListener>()

    fun setOnResultListener(listener: OnResultListener) = listeners.add(listener)

    fun loginUser(user: UserLoginRequest) {
        //Dealy for test
        //delay(2000)
        val gson = GsonBuilder()
            .setLenient()
            .create()
        cookieHandler = CookieHandler()
        val client = OkHttpClient()
            .newBuilder()
            .cookieJar(cookieHandler)
            .build()
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        val ktistaAPI = retrofit.create(KtistaAPI::class.java)
        val call = ktistaAPI.login(user)
        call?.enqueue(this)
    }

    companion object {
        const val BASE_URL = "http://192.168.0.231:8080/"
    }

    override fun onResponse(call: Call<String?>, response: Response<String?>) {
        if (response.isSuccessful) {
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

    override fun onFailure(call: Call<String?>, t: Throwable) {
        for(listener in listeners)
            listener.onFail()
        t.printStackTrace()
    }
}