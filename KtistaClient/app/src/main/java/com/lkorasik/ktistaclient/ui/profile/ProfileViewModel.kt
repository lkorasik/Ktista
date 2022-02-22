package com.lkorasik.ktistaclient.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.repository.ProfileRepository
import com.lkorasik.ktistaclient.ui.TestDataClass
import com.lkorasik.ktistaclient.ui.helper.converters.ConvertPost
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

    private val requestProgress = MutableLiveData(RequestStages.INIT)
    val profile = MutableLiveData<ProfileModel>().apply {
        this.postValue(ProfileModel())
    }

    private val profileRepository = ProfileRepository()

    private val mutablePostsData: MutableLiveData<List<PostModel>> = MutableLiveData()
    val postsData: LiveData<List<PostModel>> = mutablePostsData

    fun getUsersPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LOG_TAG, "Start request get user's posts")
            val result = profileRepository.getUsersPosts()
            Log.i(LoginViewModel.LOG_TAG, "End get user's posts request. Status: ${if(result.isSuccessful) "Success" else "Failed"}")

            if(result.isSuccessful){
                Log.i(LOG_TAG, "Get user's posts request was success")

                result.body()?.let {
                    mutablePostsData.postValue(it.map { post -> ConvertPost.convert(post) })
                }
            } else {
                Log.i(LOG_TAG, "Get user's posts request was failed")
            }
        }
    }

    fun getProfile() {
        requestProgress.value = RequestStages.IN_PROGRESS

        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LOG_TAG, "Start request get profile")
            val result = profileRepository.getProfile()
            Log.i(LoginViewModel.LOG_TAG, "End get profile request. Status: ${if(result.isSuccessful) "Success" else "Failed"}")

            if(result.isSuccessful){
                requestProgress.postValue(RequestStages.SUCCESS)

                result.body()?.let {
                    profile.postValue(ConvertProfile.convert(it))
                }
            } else {
                requestProgress.postValue(RequestStages.FAIL)
            }
        }
    }
}
