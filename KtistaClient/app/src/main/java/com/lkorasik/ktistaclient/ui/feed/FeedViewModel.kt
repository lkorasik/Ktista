package com.lkorasik.ktistaclient.ui.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.repository.FeedRepository
import com.lkorasik.ktistaclient.ui.helper.converters.ConvertPost
import com.lkorasik.ktistaclient.ui.models.PostModel
import com.lkorasik.ktistaclient.ui.profile.ProfileViewModel
import com.lkorasik.ktistaclient.ui.start.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FeedViewModel : ViewModel() {
    companion object {
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val mutablePostsData: MutableLiveData<List<PostModel>> = MutableLiveData()
    val postsData: LiveData<List<PostModel>> = mutablePostsData

    val repository = FeedRepository()

    fun getFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LOG_TAG, "Start request get feed")
            val result = repository.getFeed()
            Log.i(LOG_TAG, "End get feed request. Status: ${if(result.isSuccessful) "Success" else "Failed"}")

            if(result.isSuccessful){
                result.body()?.apply {
                    mutablePostsData.postValue(map { ConvertPost.convert(it) })
                }
            }
        }
    }
}
