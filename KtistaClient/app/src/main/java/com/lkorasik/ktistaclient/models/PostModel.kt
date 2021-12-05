package com.lkorasik.ktistaclient.models

class PostModel(
    var user: UserModel,
    var photoUrl: String,
    var likeCount: String = "",
    var description: String = "",
    var dislikeCount: String = "",
    var commentCount: String = "",
)
