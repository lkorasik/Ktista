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
                listener.onSuccess(response.body(), response.headers())
            Log.i("KtistaAppHttp", "Request success")
        }
        else{
            for(listener in listeners)
                listener.onFail()
            Log.i("KtistaAppHttp", "Request fail")
            Log.e("KtistaAppHttp", response.errorBody().toString())
        }
    }

    open override fun onFailure(call: Call<T?>, t: Throwable) {
        for(listener in listeners)
            listener.onFail()
        t.printStackTrace()
    }
}