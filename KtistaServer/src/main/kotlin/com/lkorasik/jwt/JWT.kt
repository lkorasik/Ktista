package com.lkorasik.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JWT(secret: String) {
    private val algorithm: Algorithm = Algorithm.HMAC256(secret)
    val verifier: JWTVerifier = JWT.require(algorithm).build()

    fun sign(name: String): String {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.MINUTE, 1)
        }

        val tokenExpirationDate = calendar.time

        return JWT.create()
            .withClaim("name", name)
            .withExpiresAt(tokenExpirationDate)
            .sign(algorithm)
    }
}

