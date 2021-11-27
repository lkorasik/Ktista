package com.lkorasik.ktistaclient.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lkorasik.ktistaclient.models.PostModel
import com.lkorasik.ktistaclient.models.UserModel


class FeedViewModel : ViewModel() {

    private val mutablePostsData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()
    val postsData: LiveData<ArrayList<PostModel>> = mutablePostsData

    init {
        testLoadPosts()
    }

    private fun testLoadPosts() {
        val photo =
            "https://v1.popcornnews.ru/k2/news/1200/upload/news/414696148775.jpg"
        val text = "texttexttexttexttextte"
        val usr = UserModel(
            avatarUrl = photo,
            name = "Bob",
            login = "@bobik"
        )
        mutablePostsData.postValue(
            arrayListOf(
                PostModel(
                    user = usr,
                    photoUrl = photo,
                    description = text,
                    likeCount = "322",
                    dislikeCount = "1337"
                ),
                PostModel(
                    user = usr,
                    photoUrl = photo,
                    likeCount = "322",
                    dislikeCount = "1337",
                    commentCount = "232"
                ),
                PostModel(
                    user = usr,
                    photoUrl = photo,
                    likeCount = "2",
                    dislikeCount = "373232",
                    commentCount = "232"
                ),
                PostModel(
                    user = usr,
                    photoUrl = photo,
                    description = text,
                    likeCount = "32",
                    dislikeCount = "13127",
                    commentCount = "232"
                )
            )
        )
    }


    private fun loadPosts() {}
}
