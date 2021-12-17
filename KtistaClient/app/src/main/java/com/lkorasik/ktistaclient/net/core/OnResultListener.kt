package com.lkorasik.ktistaclient.net.core

import okhttp3.Headers

interface OnResultListener<ResponseType>{
    fun onSuccess(body: ResponseType?, headers: Headers)
    fun onFail()
}