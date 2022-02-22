package com.lkorasik.ktistaclient.ui.follow.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lkorasik.ktistaclient.ui.models.FollowModel
import com.lkorasik.ktistaclient.ui.TestDataClass

class FollowersViewModel : ViewModel() {
    private val mutableFollowersData: MutableLiveData<ArrayList<FollowModel>> = MutableLiveData()
    val followersData: LiveData<ArrayList<FollowModel>> = mutableFollowersData

    init {
        testLoadPosts()
    }

    fun testLoadPosts() {
        mutableFollowersData.postValue(TestDataClass.getFollowData())
    }

    private fun loadFollowers() {}
}
