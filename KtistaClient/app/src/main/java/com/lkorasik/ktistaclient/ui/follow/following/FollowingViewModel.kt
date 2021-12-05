package com.lkorasik.ktistaclient.ui.follow.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lkorasik.ktistaclient.models.FollowModel
import com.lkorasik.ktistaclient.models.UserModel
import com.lkorasik.ktistaclient.ui.TestDataClass

class FollowingViewModel : ViewModel() {

    private val mutableFollowingData: MutableLiveData<ArrayList<FollowModel>> = MutableLiveData()
    val followingData: LiveData<ArrayList<FollowModel>> = mutableFollowingData

    init {
        testLoadPosts()
    }

    fun testLoadPosts() {
       mutableFollowingData.postValue(TestDataClass.getFollowData())
    }

    private fun loadFollowers() {}
}