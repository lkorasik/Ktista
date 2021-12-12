package com.lkorasik.ktistaclient.net.requests

interface OnResultListener<ResponseType>{
    fun onSuccess(obj: ResponseType?)
    fun onFail()
}