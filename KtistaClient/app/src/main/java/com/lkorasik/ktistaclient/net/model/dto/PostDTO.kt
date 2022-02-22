package com.lkorasik.ktistaclient.net.model.dto

data class PostDTO(
    var author: AuthorDTO,
    var text: String,
    var data: String
)
