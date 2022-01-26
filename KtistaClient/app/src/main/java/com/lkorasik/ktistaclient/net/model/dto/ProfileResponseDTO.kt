package com.lkorasik.ktistaclient.net.model.dto

data class ProfileResponseDTO(
    val image: String?,
    val username: String,
    val followers: Int,
    val followings: Int
)