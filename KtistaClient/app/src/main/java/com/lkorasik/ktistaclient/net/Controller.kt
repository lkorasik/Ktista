package com.lkorasik.ktistaclient.net

import android.content.Context
import android.util.Log
import com.google.gson.*
import com.lkorasik.ktistaclient.net.model.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class Controller(val context: Context, val success: (resp: UserRegistrationResponse) -> Unit, val fail: () -> Unit) : Callback<UserRegistrationResponse?> {
    fun registerUser(user: UserRegistrationRequest) {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
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
            val headers = response.headers()
            val cookie = headers.values("Set-Cookie")
            val kv = HashMap<String, String>()
            cookie.forEach{
                val entry = it.split("=")
                kv[entry[0]] = entry[1]
            }
            success(user)
            Log.i("KtistaAppHttp", "Registration success!!!")
        } else {
            fail()
            Log.i("KtistaAppHttp", "Registration fail!!!")
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<UserRegistrationResponse?>, t: Throwable) {
        fail()
        t.printStackTrace()
    }
}