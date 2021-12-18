package com.lkorasik.ktistaclient.ui.feed.addPost

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.OnResultListener
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.dto.CreatePostDTO
import com.lkorasik.ktistaclient.net.requests.CreatePostRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Headers
import java.io.File

class AddPostViewModel: ViewModel() {
    companion object{
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val createPost = CreatePostRequest().apply {
        setOnResultListener(object : OnResultListener<String> {
            override fun onSuccess(body: String?, headers: Headers) {
                inProgress.value = RequestStages.SUCCESS
                Log.i(LOG_TAG, "Request was success")
            }

            override fun onFail() {
                inProgress.value = RequestStages.FAIL
                Log.i(LOG_TAG, "Request was failed")
            }
        })
    }

    val inProgress = MutableLiveData(RequestStages.INIT)

    fun createPost(id: Long, text: String, imagePath: String){
        viewModelScope.launch(Dispatchers.IO) {
            inProgress.postValue(RequestStages.IN_PROGRESS)
            Log.i(LOG_TAG, "Start request")

            val bytes = File(imagePath).readBytes()

            createPost.createPost(CreatePostDTO(id, text, bytes))
        }
    }
}
