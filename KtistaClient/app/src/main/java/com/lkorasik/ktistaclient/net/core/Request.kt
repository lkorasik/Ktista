package com.lkorasik.ktistaclient.net.core

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class Request<T>: Callback<T?> {
    private var listeners = mutableListOf<OnResultListener<T>>()

    fun setOnResultListener(listener: OnResultListener<T>) = listeners.add(listener)

    open override fun onResponse(call: Call<T?>, response: Response<T?>){
        if (response.isSuccessful){
            for(listener in listeners)
                listener.onSuccess(response.body())
            Log.i("KtistaAppHttp", "Registration success!!!")
        }
        else{
            for(listener in listeners)
                listener.onFail()
            Log.i("KtistaAppHttp", "Registration fail!!!")
        }
    }

    open override fun onFailure(call: Call<T?>, t: Throwable) {
        for(listener in listeners)
            listener.onFail()
        t.printStackTrace()
    }
}