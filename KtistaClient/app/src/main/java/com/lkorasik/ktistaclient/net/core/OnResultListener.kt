package com.lkorasik.ktistaclient.net.core

import okhttp3.Headers

interface OnResultListener<ResponseType>{
    fun onSuccess(obj: ResponseType?, headers: Headers)
    fun onFail()
}