package com.lkorasik.ktistaclient.ui.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.OnResultListener
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.dto.ProfileResponseDTO
import com.lkorasik.ktistaclient.net.model.dto.SettingsRequestDTO
import com.lkorasik.ktistaclient.net.model.dto.SettingsResponseDTO
import com.lkorasik.ktistaclient.net.requests.SettingsRequest
import com.lkorasik.ktistaclient.ui.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Headers
import java.security.interfaces.RSAKey

class SettingsViewModel : ViewModel() {
    companion object {
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    private val getSettingsRequest = SettingsRequest().apply {
        setOnResultListener(object : OnResultListener<SettingsResponseDTO> {
            override fun onSuccess(body: SettingsResponseDTO?, headers: Headers) {
                body?.let {
                    data.postValue(it)
                    Log.i(ProfileViewModel.LOG_TAG, "Request get settings was success")
                }
            }

            override fun onFail() {
                inProgress.postValue(RequestStages.FAIL)
                Log.i(ProfileViewModel.LOG_TAG, "Request get settings was failed")
            }
        })
    }

    val inProgress = MutableLiveData(RequestStages.INIT)
    val data = MutableLiveData<SettingsResponseDTO>()

    fun getSettings(){
        inProgress.value = RequestStages.IN_PROGRESS

        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LOG_TAG, "Start request get settings")
            getSettingsRequest.getSettings(SettingsRequestDTO(1))
        }
    }
}