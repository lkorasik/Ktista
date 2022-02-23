package com.lkorasik.ktistaclient.net.repository

import com.lkorasik.ktistaclient.net.core.RequestContext
import com.lkorasik.ktistaclient.net.model.dto.PostDTO
import retrofit2.Response

class FeedRepository {
    fun getFeed(): Response<List<PostDTO>> {
        return RequestContext.API.getFeed(JwtRepository.jwtAccess).execute()
    }
}
