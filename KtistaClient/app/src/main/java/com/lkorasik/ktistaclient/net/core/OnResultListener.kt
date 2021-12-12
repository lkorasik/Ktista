package com.lkorasik.ktistaclient.net.core

interface OnResultListener<ResponseType>{
    fun onSuccess(obj: ResponseType?)
    fun onFail()
}