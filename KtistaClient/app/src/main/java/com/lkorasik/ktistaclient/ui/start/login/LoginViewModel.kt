package com.lkorasik.ktistaclient.ui.start.login

import android.util.Log
import androidx.lifecycle.*
import com.lkorasik.ktistaclient.net.requests.login.LoginRequest
import com.lkorasik.ktistaclient.net.requests.login.LoginStages
import com.lkorasik.ktistaclient.net.requests.OnResultListener
import com.lkorasik.ktistaclient.net.model.UserLoginRequest
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    companion object{
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val loginRequest = LoginRequest().apply {
        setOnResultListener(object : OnResultListener {
            override fun onSuccess() {
                inProgress.value = LoginStages.SUCCESS
                Log.i(LOG_TAG, "Request was success")
            }

            override fun onFail() {
                inProgress.value = LoginStages.FAIL
                Log.i(LOG_TAG, "Login was success")
            }
        })
    }

    val inProgress = MutableLiveData(LoginStages.INIT)

    fun loginUser(nickname: String, password: String){
        viewModelScope.launch {
            Log.i(LOG_TAG, "Start request")
            loginRequest.loginUser(UserLoginRequest(nickname, password))
        }
    }
}