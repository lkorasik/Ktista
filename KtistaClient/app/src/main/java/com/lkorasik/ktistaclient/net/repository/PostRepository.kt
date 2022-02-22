package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.CreatePostDTO
import retrofit2.Response

class PostRepository {
    fun createPost(post: CreatePostDTO): Response<String?> {
        return RequestContext.API.createPost(JwtRepository.jwtAccess, post).execute()
    }
}
