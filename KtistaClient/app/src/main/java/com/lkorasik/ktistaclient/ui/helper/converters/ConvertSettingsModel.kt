package com.lkorasik.ktistaclient.ui.helper.converters

import android.graphics.Bitmap
import android.util.Base64
import com.lkorasik.ktistaclient.net.model.dto.SettingsDTO
import com.lkorasik.ktistaclient.ui.models.SettingsModel
import java.io.ByteArrayOutputStream

object ConvertSettingsModel: Converter<SettingsModel, SettingsDTO> {
    override fun convert(input: SettingsModel): SettingsDTO {
        val byteArrayOutputStream = ByteArrayOutputStream()
        input.avatar?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        val avatar = Base64.encodeToString(byteArray, Base64.DEFAULT)

        return SettingsDTO(
            avatar = avatar,
            email = input.email,
            nickname = input.username
        )
    }
}