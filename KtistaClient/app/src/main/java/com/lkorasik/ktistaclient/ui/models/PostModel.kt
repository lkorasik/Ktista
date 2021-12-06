package com.lkorasik.ktistaclient.ui.models

data class PostModel(
    var user: UserModel,
    var photoUrl: String,
    var likeCount: Int = 0,
    var description: String = "",
    var dislikeCount: Int = 0,
    var commentCount: Int = 0,
    var date: String = "",
)
