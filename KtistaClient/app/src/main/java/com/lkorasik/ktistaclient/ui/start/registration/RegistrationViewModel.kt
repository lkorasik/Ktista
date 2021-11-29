package com.lkorasik.ktistaclient.ui.start.registration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.OnRegistrationResultListener
import com.lkorasik.ktistaclient.net.RegistrationRequest
import com.lkorasik.ktistaclient.net.RegistrationStages
import com.lkorasik.ktistaclient.net.model.UserRegistrationRequest
import kotlinx.coroutines.launch

class RegistrationViewModel: ViewModel(){
    companion object{
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val registrationRequest: RegistrationRequest = RegistrationRequest().apply {
        setOnRegistrationResultListener(object: OnRegistrationResultListener{
            override fun onSuccess() {
                inProgress.value = RegistrationStages.SUCCESS
                Log.i(LOG_TAG, "Request was success ${inProgress.value}")
            }
            override fun onFail() {
                inProgress.value = RegistrationStages.FAIL
                Log.i(LOG_TAG, "Request was fail ${inProgress.value}")
            }
        })
    }

    val inProgress = MutableLiveData(RegistrationStages.INIT)

    fun registerUser(nickname: String, password: String, email: String){
        viewModelScope.launch {
            inProgress.value = RegistrationStages.IN_PROGRESS
            Log.i(LOG_TAG, "Start request ${inProgress.value}")
            registrationRequest.registerUser(UserRegistrationRequest(nickname, password, email))
        }
    }
}
