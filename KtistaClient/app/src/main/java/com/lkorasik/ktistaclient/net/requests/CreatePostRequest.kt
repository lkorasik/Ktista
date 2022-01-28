package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.CreatePostDTO
import com.lkorasik.ktistaclient.net.repository.JwtRepository

class CreatePostRequest: Request<String>() {
    private val jwtRepository = JwtRepository()

    fun createPost(post: CreatePostDTO) = RequestContext.API.createPost("Bearer ${jwtRepository.jwt}", post)?.enqueue(this)
}