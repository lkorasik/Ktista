package com.lkorasik.ktistaclient.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.OnResultListener
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.dto.ProfileRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.ProfileResponseDTO
import com.lkorasik.ktistaclient.net.requests.ProfileRequest
import com.lkorasik.ktistaclient.ui.TestDataClass
import com.lkorasik.ktistaclient.ui.helper.converters.ConvertProfile
import com.lkorasik.ktistaclient.ui.models.PostModel
import com.lkorasik.ktistaclient.ui.models.ProfileModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Headers

class ProfileViewModel : ViewModel() {
    companion object {
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val getProfileRequest = ProfileRequest().apply {
        setOnResultListener(object : OnResultListener<ProfileResponseDTO> {
            override fun onSuccess(body: ProfileResponseDTO?, headers: Headers) {
                body?.let {
                    data.value?.apply {
                        ConvertProfile.convert(it, this)
                    }

                    data.postValue(data.value)
                    Log.i(LOG_TAG, "Request get profile was success")
                }
            }

            override fun onFail() {
                inProgress.postValue(RequestStages.FAIL)
                Log.i(LOG_TAG, "Request get profile was failed")
            }
        })
    }

    val inProgress = MutableLiveData(RequestStages.INIT)
    val data = MutableLiveData<ProfileModel>().apply {
        this.postValue(ProfileModel())
    }

    private val mutablePostsData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()
    val postsData: LiveData<ArrayList<PostModel>> = mutablePostsData

    init {
        testLoadPosts()
    }

    private fun testLoadPosts() {
        mutablePostsData.postValue(TestDataClass.getPostsData())
    }

    private fun loadPosts() {}

    fun getProfile() {
        inProgress.value = RequestStages.IN_PROGRESS

        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LOG_TAG, "Start request get profile")
            getProfileRequest.getProfile(ProfileRequestDTO(1))
        }
    }
}

