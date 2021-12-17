package com.lkorasik.ktistaclient.net.model.dto

data class CreatePostDTO(val userId: Long, val text: String, val data: ByteArray)