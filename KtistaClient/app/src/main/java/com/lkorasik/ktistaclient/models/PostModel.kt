package com.lkorasik.ktistaclient.models

class PostModel(
    var user: UserModel,
    var photo: String,
    var likeCount: String = "",
    var description: String = "",
    var dislikeCount: String = "",
    var commentCount: String = "",
)
