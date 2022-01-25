package com.lkorasik.ktistaclient.net.model.dto

data class SettingsResponseDTO(
    val avatar: ByteArray?,
    val email: String,
    val nickname: String
)