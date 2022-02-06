package com.lkorasik.ktistaclient.ui.models

import android.graphics.Bitmap

data class PostModel(
    var user: UserModel,
    var photo: Bitmap,
    var likeCount: Int = 0,
    var description: String = "",
    var dislikeCount: Int = 0,
    var commentCount: Int = 0,
    var date: String = "",
)
