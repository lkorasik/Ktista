package com.lkorasik.ktistaclient.ui.feed.addPost

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.OnResultListener
import com.lkorasik.ktistaclient.net.model.dto.CreatePostDTO
import com.lkorasik.ktistaclient.net.requests.CreatePostRequest
import kotlinx.coroutines.launch
import okhttp3.Headers

class AddPostViewModel: ViewModel() {
    companion object{
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val createPost = CreatePostRequest().apply {
        setOnResultListener(object : OnResultListener<String> {
            override fun onSuccess(body: String?, headers: Headers) {
                Log.i(LOG_TAG, "Request was success")
            }

            override fun onFail() {
                Log.i(LOG_TAG, "Request was failed")
            }
        })
    }

    fun createPost(id: Long, text: String, data: ByteArray){
        viewModelScope.launch {
            Log.i(LOG_TAG, "Start request")
            createPost.createPost(CreatePostDTO(id, text, data))
        }
    }
}
