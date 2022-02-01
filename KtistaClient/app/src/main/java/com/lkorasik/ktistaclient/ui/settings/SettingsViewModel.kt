package com.lkorasik.ktistaclient.ui.settings

import android.util.Base64
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkorasik.ktistaclient.net.core.RequestStages
import com.lkorasik.ktistaclient.net.model.dto.SettingsDTO
import com.lkorasik.ktistaclient.net.repository.SettingsRepository
import com.lkorasik.ktistaclient.ui.helper.converters.ConvertSettings
import com.lkorasik.ktistaclient.ui.models.SettingsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    companion object {
        val LOG_TAG: String = this::class.qualifiedName.toString()
    }

    val requestStage = MutableLiveData(RequestStages.INIT)
    val data = MutableLiveData<SettingsModel>().apply {
        postValue(SettingsModel())
    }

    private val settingsRepository = SettingsRepository()

    fun setSettings(avatar: ByteArray?, email: String, username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LOG_TAG, "Start request set settings")

            val avatarImg = if (avatar != null) Base64.encodeToString(avatar, Base64.DEFAULT) else ""

            settingsRepository.setSettings(SettingsDTO(
                avatar = avatarImg,
                email = email,
                nickname = username,
            ))
            Log.i(LOG_TAG, "End get settings request")
        }
    }

    fun getSettings() {
        requestStage.value = RequestStages.IN_PROGRESS

        viewModelScope.launch(Dispatchers.IO) {
            Log.i(LOG_TAG, "Start request get settings")
            val result = settingsRepository.getSettings()
            Log.i(LOG_TAG, "End get settings request. Status: ${if(result.isSuccessful) "Success" else "Failed"}")

            if(result.isSuccessful){
                requestStage.postValue(RequestStages.SUCCESS)

                result.body()?.let {
                    data.postValue(ConvertSettings.convert(it))
                }
            } else {
                requestStage.postValue(RequestStages.FAIL)
            }
        }
    }
}