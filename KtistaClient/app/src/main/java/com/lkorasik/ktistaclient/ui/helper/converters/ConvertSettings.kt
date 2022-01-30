package com.lkorasik.ktistaclient.ui.helper.converters

import android.util.Base64
import com.lkorasik.ktistaclient.net.model.dto.SettingsResponseDTO
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.ui.models.SettingsModel

object ConvertSettings: Converter<SettingsResponseDTO, SettingsModel> {
    override fun convert(input: SettingsResponseDTO): SettingsModel {
        val settingsModel = SettingsModel()

        if((input.avatar != null) && input.avatar.isNotEmpty()) {
            val bytes = Base64.decode(input.avatar, Base64.DEFAULT)
            val bmp = ImageHelper.convertToBitmap(bytes)

            settingsModel.avatar = bmp
        }

        settingsModel.username = input.nickname
        settingsModel.email = input.email

        return settingsModel
    }
}