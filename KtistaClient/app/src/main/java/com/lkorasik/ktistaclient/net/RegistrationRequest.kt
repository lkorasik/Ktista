package com.lkorasik.ktistaclient.net

import android.util.Log
import com.google.gson.*
import com.lkorasik.ktistaclient.net.model.*
import okhttp3.*
import retrofit2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationRequest : Callback<UserRegistrationResponse?> {
    private lateinit var cookieHandler: CookieHandler
    private var listeners = mutableListOf<OnResultListener>()

    fun setOnResultListener(listener: OnResultListener) = listeners.add(listener)

    fun registerUser(user: UserRegistrationRequest) {
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
        val call = ktistaAPI.register(user)
        call?.enqueue(this)
    }

    companion object {
        const val BASE_URL = "http://192.168.0.231:8080/"
    }

    override fun onResponse(call: Call<UserRegistrationResponse?>, response: Response<UserRegistrationResponse?>) {
        if (response.isSuccessful) {
            val user = response.body()!!
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