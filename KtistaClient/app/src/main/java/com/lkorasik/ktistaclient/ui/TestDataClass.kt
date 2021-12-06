package com.lkorasik.ktistaclient.ui

import com.lkorasik.ktistaclient.ui.models.FollowModel
import com.lkorasik.ktistaclient.ui.models.PostModel
import com.lkorasik.ktistaclient.ui.models.UserModel

object TestDataClass {
    fun getPostsData(): ArrayList<PostModel> {
        val photo =
            "https://v1.popcornnews.ru/k2/news/1200/upload/news/414696148775.jpg"
        val text = "texttexttexttexttextte"
        val usr = UserModel(
            avatarUrl = photo,
            name = "Bob",
            login = "@bobik"
        )
        return arrayListOf(
            PostModel(
                user = usr,
                photoUrl = photo,
                description = text,
                likeCount = 322,
                dislikeCount = 1337,
                date = "17 november"
            ),
            PostModel(
                user = usr,
                photoUrl = photo,
                likeCount = 322,
                dislikeCount = 1337,
                commentCount = 232,
                date = "17 november"
            ),
            PostModel(
                user = usr,
                photoUrl = photo,
                likeCount = 2,
                dislikeCount = 1337,
                commentCount = 232,
                date = "17 november"
            ),
            PostModel(
                user = usr,
                photoUrl = photo,
                description = text,
                likeCount = 32,
                dislikeCount = 13127,
                commentCount = 232,
                date = "17 november"
            )
        )
    }

    fun getFollowData(): ArrayList<FollowModel> {
        val photo =
            "https://v1.popcornnews.ru/k2/news/1200/upload/news/414696148775.jpg"
        val usr = UserModel(
            avatarUrl = photo,
            name = "Bob",
            login = "@bobik"
        )
        return arrayListOf(
            FollowModel(
                user = usr,
                isFollow = true

            ),
            FollowModel(
                user = usr,
                isFollow = false

            ),
            FollowModel(
                user = usr,
                isFollow = true

            ),
            FollowModel(
                user = usr,
                isFollow = false

            ),
        )
    }
}