package com.lkorasik.ktistaclient.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.repository.ProfileRepository
import com.lkorasik.ktistaclient.ui.TestDataClass
import com.lkorasik.ktistaclient.ui.helper.converters.ConvertProfile
import com.lkorasik.ktistaclient.ui.models.PostModel
import com.lkorasik.ktistaclient.ui.models.ProfileModel
import com.lkorasik.ktistaclient.ui.start.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    companion object {
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    val inProgress = MutableLiveData(RequestStages.INIT)
    val data = MutableLiveData<ProfileModel>().apply {
        this.postValue(ProfileModel())
    }

    private val profileRepository = ProfileRepository()

    private val mutablePostsData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()
    val postsData: LiveData<ArrayList<PostModel>> = mutablePostsData

    fun testLoadPosts() {
        mutablePostsData.postValue(TestDataClass.getPostsData())
    }

    private fun loadPosts() {}

    fun getProfile() {
        inProgress.value = RequestStages.IN_PROGRESS

        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LOG_TAG, "Start request get profile")
            val result = profileRepository.getProfile(1)
            Log.i(LoginViewModel.LOG_TAG, "End get profile request. Status: ${if(result.isSuccessful) "Success" else "Failed"}")

            if(result.isSuccessful){
                inProgress.postValue(RequestStages.SUCCESS)

                result.body()?.let {
                    data.value?.apply {
                        ConvertProfile.convert(it, this)
                    }

                    data.postValue(data.value)
                }
            } else {
                inProgress.postValue(RequestStages.FAIL)
            }
        }
    }
}

