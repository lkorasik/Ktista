package com.lkorasik.ktistaclient.changepassword

interface OnCompleteListener<T>{
    /**
     * Call this method after finish work
     */
    fun onComplete(container: T)
}