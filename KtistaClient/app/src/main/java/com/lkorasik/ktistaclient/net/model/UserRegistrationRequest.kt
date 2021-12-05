package com.lkorasik.ktistaclient.net.model

data class UserRegistrationRequest(val username: String, val password: String, var email:String)