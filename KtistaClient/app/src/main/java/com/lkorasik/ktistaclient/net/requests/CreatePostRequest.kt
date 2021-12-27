package com.lkorasik.ktistaclient.net.requests

import com.lkorasik.ktistaclient.net.core.Request
import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.JWTTempStorage
import com.lkorasik.ktistaclient.net.model.dto.CreatePostDTO

class CreatePostRequest: Request<String>() {
    fun createPost(post: CreatePostDTO) = RequestContext.API.createPost("Bearer ${JWTTempStorage.jwt}", post)?.enqueue(this)
}