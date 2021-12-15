package com.lkorasik.ktistaclient.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.model.GetProfileRequest
import com.lkorasik.ktistaclient.net.model.ProfileResponse
import com.lkorasik.ktistaclient.net.core.OnResultListener
import com.lkorasik.ktistaclient.net.requests.ProfileRequest
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.ui.models.PostModel
import com.lkorasik.ktistaclient.ui.TestDataClass
import android.graphics.BitmapFactory
import android.R
import android.provider.MediaStore
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import android.os.Bundle
import android.app.Activity
import android.view.View
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    companion object{
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val getProfileRequest = ProfileRequest().apply {
        setOnResultListener(object : OnResultListener<ProfileResponse> {
            override fun onSuccess(obj: ProfileResponse?) {
                inProgress.value = RequestStages.SUCCESS
                obj.let {
                    data.value = it
                    Log.i(LOG_TAG, "Request get profile was success")
                }
            }

            override fun onFail() {
                inProgress.value = RequestStages.FAIL
                Log.i(LOG_TAG, "Request get profile was failed")
            }

        })
    }

    val inProgress = MutableLiveData(RequestStages.INIT)
    val data = MutableLiveData<ProfileResponse>()

    private val mutablePostsData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()
    val postsData: LiveData<ArrayList<PostModel>> = mutablePostsData

    init {
        testLoadPosts()
    }

    private fun testLoadPosts() {
        mutablePostsData.postValue(TestDataClass.getPostsData())
    }

    private fun loadPosts() {}

    fun getProfile(){
        viewModelScope.launch {
            inProgress.value = RequestStages.IN_PROGRESS
            Log.i(LOG_TAG, "Start request get profile")
            getProfileRequest.getProfile(GetProfileRequest(1))
        }
    }
}
