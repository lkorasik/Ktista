package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.CreatePostDTO
import com.lkorasik.ktistaclient.net.repository.JwtRepository

class CreatePostRequest: Request<String>() {
    fun createPost(post: CreatePostDTO) = RequestContext.API.createPost("Bearer ${JwtRepository.jwt}", post).enqueue(this)
}