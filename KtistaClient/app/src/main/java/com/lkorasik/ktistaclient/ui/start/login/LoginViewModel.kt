package com.lkorasik.ktistaclient.ui.start.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.dto.UserLoginRequestDTO
import com.lkorasik.ktistaclient.net.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    companion object {
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    val inProgress = MutableLiveData(RequestStages.INIT)

    fun loginUser(nickname: String, password: String) {
        inProgress.value = RequestStages.IN_PROGRESS

        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LOG_TAG, "Start login request")
            val result = userRepository.login(UserLoginRequestDTO(nickname, password))
            Log.i(LOG_TAG, "End login request. Status: ${if(result.isSuccessful) "Success" else "Failed"}")

            if(result.isSuccessful){
                inProgress.postValue(RequestStages.SUCCESS)
            } else {
                inProgress.postValue(RequestStages.FAIL)
            }
        }
    }
}
