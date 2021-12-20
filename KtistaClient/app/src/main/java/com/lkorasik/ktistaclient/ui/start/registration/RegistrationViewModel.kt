package com.lkorasik.ktistaclient.ui.start.registration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.OnResultListener
import com.lkorasik.ktistaclient.net.requests.RegistrationRequest
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.*
import com.lkorasik.ktistaclient.net.requests.LoginRequest
import kotlinx.coroutines.launch
import okhttp3.Headers

class RegistrationViewModel: ViewModel(){
    companion object{
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val registrationRequest = RegistrationRequest().apply {
        setOnResultListener(object: OnResultListener<UserRegistrationResponse> {
            override fun onSuccess(obj: UserRegistrationResponse?, headers: Headers) {
                //inProgress.value = RequestStages.SUCCESS
                loginRequest.loginUser(UserLoginRequest(nickname, password))
                Log.i(LOG_TAG, "Registration request was success ${inProgress.value}")
            }
            override fun onFail() {
                inProgress.value = RequestStages.FAIL
                Log.i(LOG_TAG, "Registration request was fail ${inProgress.value}")
            }
        })
    }

    private val loginRequest = LoginRequest().apply {
        setOnResultListener(object : OnResultListener<Void> {
            override fun onSuccess(body: Void?, headers: Headers) {
                inProgress.value = RequestStages.SUCCESS
                JWTTempStorage.jwt = headers[HeadersKeys.AUTHORIZATION.toString()].toString().split(" ")[1]
                Log.i(LOG_TAG, "Login was success")
            }

            override fun onFail() {
                inProgress.value = RequestStages.FAIL
                Log.i(LOG_TAG, "Login was failed")
            }
        })
    }

    val inProgress = MutableLiveData(RequestStages.INIT)

    private var nickname: String = ""
    private var password: String = ""

    fun registerUser(nickname: String, password: String, email: String){
        this.nickname = nickname
        this.password = password

        viewModelScope.launch {
            inProgress.value = RequestStages.IN_PROGRESS
            Log.i(LOG_TAG, "Start request ${inProgress.value}")
            registrationRequest.registerUser(UserRegistrationRequest(nickname, password, email))
        }
    }
}
