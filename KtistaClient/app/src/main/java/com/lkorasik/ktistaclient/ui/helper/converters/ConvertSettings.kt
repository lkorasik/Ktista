package com.lkorasik.ktistaclient.ui.helper.converters

import android.util.Base64
import com.lkorasik.ktistaclient.net.model.dto.SettingsDTO
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.ui.models.SettingsModel

object ConvertSettings: Converter<SettingsDTO, SettingsModel> {
    override fun convert(input: SettingsDTO): SettingsModel {
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