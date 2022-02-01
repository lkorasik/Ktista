package com.lkorasik.ktistaclient.ui.feed.addPost

import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.OnResultListener
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.dto.CreatePostDTO
import com.lkorasik.ktistaclient.net.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Headers
import java.io.File
import java.util.*

class AddPostViewModel : ViewModel() {
    companion object {
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    val inProgress = MutableLiveData(RequestStages.INIT)

    private val postRepository = PostRepository()

    private fun createPost(text: String, getBytes: () -> ByteArray?) {
        inProgress.value = RequestStages.IN_PROGRESS

        viewModelScope.launch(Dispatchers.IO) {
            getBytes()?.let {
                val data = getBase64Image(byteArray = it)

                Log.i(LOG_TAG, "Start request create post")
                val result = postRepository.createPost(CreatePostDTO(text, data))
                Log.i(LOG_TAG, "End create post request.")

                if(result.isSuccessful){
                    inProgress.postValue(RequestStages.SUCCESS)
                    Log.i(LOG_TAG, "Create post request was successful.")
                } else {
                    inProgress.postValue(RequestStages.FAIL)
                    Log.i(LOG_TAG, "Create post request was failed.")
                }
            }
        }
    }

    private suspend fun getBase64Image(byteArray: ByteArray): String {
        return withContext(Dispatchers.Default) {
            Base64.getEncoder().encodeToString(byteArray)
        }
    }

    fun createPost(contentResolver: ContentResolver, text: String, imagePath: String) {
        createPost(text) {
            contentResolver.openInputStream(Uri.parse(imagePath))?.readBytes()
        }
    }

    fun createPost(text: String, imagePath: String) {
        createPost(text) {
            File(imagePath).readBytes()
        }
    }
}
