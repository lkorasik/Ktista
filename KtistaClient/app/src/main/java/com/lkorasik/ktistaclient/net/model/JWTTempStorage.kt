package com.lkorasik.ktistaclient.net.model

object JWTTempStorage {
    var jwt = ""
        get() = "Bearer $field"
}