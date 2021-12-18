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
import com.lkorasik.ktistaclient.net.requests.CreatePostRequest
import com.lkorasik.ktistaclient.ui.helper.utils.PrimitiveBenchmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Headers
import java.io.File
import java.util.*

class AddPostViewModel: ViewModel() {
    companion object{
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val createPost = CreatePostRequest().apply {
        setOnResultListener(object : OnResultListener<String> {
            override fun onSuccess(body: String?, headers: Headers) {
                inProgress.value = RequestStages.SUCCESS
                Log.i(LOG_TAG, "Request was success")

                PrimitiveBenchmark.stop()
            }

            override fun onFail() {
                inProgress.value = RequestStages.FAIL
                Log.i(LOG_TAG, "Request was failed")

                PrimitiveBenchmark.stop()
            }
        })
    }

    val inProgress = MutableLiveData(RequestStages.INIT)

    private fun createPost(id: Long, text: String, getBytes: () -> ByteArray?){
        viewModelScope.launch(Dispatchers.IO) {
            inProgress.postValue(RequestStages.IN_PROGRESS)
            Log.i(LOG_TAG, "Start request")

            PrimitiveBenchmark.startMessage = "Start getting bytes"
            PrimitiveBenchmark.startMessage = "End getting bytes file"

            PrimitiveBenchmark.start()
            val bytes = getBytes()
            PrimitiveBenchmark.stop()

            bytes?.let {
                val data = Base64.getEncoder().encodeToString(it)

                PrimitiveBenchmark.startMessage = "Start request"
                PrimitiveBenchmark.startMessage = "End request"

                PrimitiveBenchmark.start()
                createPost.createPost(CreatePostDTO(id, text, data))
            }
        }
    }

    fun createPost(contentResolver: ContentResolver, id: Long, text: String, imagePath: String){
        createPost(id, text) {
            contentResolver.openInputStream(Uri.parse(imagePath))?.readBytes()
        }
    }

    fun createPost(id: Long, text: String, imagePath: String){
        createPost(id, text) {
            File(imagePath).readBytes()
        }
    }
}
