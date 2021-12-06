package com.lkorasik.ktistaclient.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lkorasik.ktistaclient.ui.models.PostModel
import com.lkorasik.ktistaclient.ui.TestDataClass

class ProfileViewModel : ViewModel() {
    private val mutablePostsData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()
    val postsData: LiveData<ArrayList<PostModel>> = mutablePostsData

    init {
        testLoadPosts()
    }

    private fun testLoadPosts() {
        mutablePostsData.postValue(TestDataClass.getPostsData())

    }

    private fun loadPosts() {}
}
