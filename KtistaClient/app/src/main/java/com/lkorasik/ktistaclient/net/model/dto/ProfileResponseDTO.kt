package com.lkorasik.ktistaclient.net.model.dto

data class ProfileResponseDTO(
    val image: ByteArray?,
    val username: String,
    val followers: Int,
    val followings: Int
)