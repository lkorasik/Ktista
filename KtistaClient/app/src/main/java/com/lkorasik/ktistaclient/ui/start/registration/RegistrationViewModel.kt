package com.lkorasik.ktistaclient.ui.start.registration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.OnResultListener
import com.lkorasik.ktistaclient.net.requests.RegistrationRequest
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.dto.UserRegistrationRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.UserRegistrationResponseDTO
import kotlinx.coroutines.launch
import okhttp3.Headers

class RegistrationViewModel: ViewModel(){
    companion object{
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val registrationRequest = RegistrationRequest().apply {
        setOnResultListener(object: OnResultListener<UserRegistrationResponseDTO> {
            override fun onSuccess(obj: UserRegistrationResponseDTO?, headers: Headers) {
                inProgress.value = RequestStages.SUCCESS
                Log.i(LOG_TAG, "Request was success ${inProgress.value}")
            }
            override fun onFail() {
                inProgress.value = RequestStages.FAIL
                Log.i(LOG_TAG, "Request was fail ${inProgress.value}")
            }
        })
    }

    val inProgress = MutableLiveData(RequestStages.INIT)

    fun registerUser(nickname: String, password: String, email: String){
        viewModelScope.launch {
            inProgress.value = RequestStages.IN_PROGRESS
            Log.i(LOG_TAG, "Start request ${inProgress.value}")
            registrationRequest.registerUser(UserRegistrationRequestDTO(nickname, password, email))
        }
    }
}
