package com.lkorasik.ktistaclient.ui.start.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.dto.UserRegistrationRequestDTO
import com.lkorasik.ktistaclient.net.repository.UserRepository
import com.lkorasik.ktistaclient.ui.start.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(private val userRepository: UserRepository) : ViewModel() {
    companion object {
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val _inProgress = MutableLiveData(RequestStages.INIT)
    val inProgress: LiveData<RequestStages> = _inProgress

    fun registerUser(nickname: String, password: String, email: String) {
        _inProgress.value = RequestStages.IN_PROGRESS

        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LoginViewModel.LOG_TAG, "Start registration request")
            //TODO("При рефакторинге убрать отсюда UserRegistrationRequestDTO")
            val result = userRepository.registration(UserRegistrationRequestDTO(nickname, password, email))
            Log.i(LoginViewModel.LOG_TAG, "End registration request. Status: ${if(result.isSuccessful) "Success" else "Failed"}")

            if(result.isSuccessful){
                _inProgress.postValue(RequestStages.SUCCESS)
            } else {
                _inProgress.postValue(RequestStages.FAIL)
            }
        }
    }
}
