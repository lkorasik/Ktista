package com.lkorasik.ktistaclient.ui.start.login

import android.util.Log
import androidx.lifecycle.*
import com.lkorasik.ktistaclient.net.requests.LoginRequest
import com.lkorasik.ktistaclient.net.core.OnResultListener
import com.lkorasik.ktistaclient.net.model.dto.UserLoginRequestDTO
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.HeadersKeys
import com.lkorasik.ktistaclient.net.model.JWTTempStorage
import kotlinx.coroutines.launch
import okhttp3.Headers

class LoginViewModel:ViewModel() {
    companion object{
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val loginRequest = LoginRequest().apply {
        setOnResultListener(object : OnResultListener<Void> {
            override fun onSuccess(body: Void?, headers: Headers) {
                inProgress.value = RequestStages.SUCCESS
                JWTTempStorage.jwt = headers[HeadersKeys.AUTHORIZATION.toString()].toString().split(" ")[1]
                Log.i(LOG_TAG, "Request was success")
            }

            override fun onFail() {
                inProgress.value = RequestStages.FAIL
                Log.i(LOG_TAG, "Login was failed")
            }
        })
    }

    val inProgress = MutableLiveData(RequestStages.INIT)

    fun loginUser(nickname: String, password: String){
        viewModelScope.launch {
            inProgress.value = RequestStages.IN_PROGRESS
            Log.i(LOG_TAG, "Start request")
            loginRequest.loginUser(UserLoginRequestDTO(nickname, password))
        }
    }
}