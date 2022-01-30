package com.lkorasik.ktistaclient.ui.helper.converters

import android.util.Base64
import com.lkorasik.ktistaclient.net.model.dto.ProfileResponseDTO
import com.lkorasik.ktistaclient.net.model.dto.SettingsResponseDTO
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.ui.models.ProfileModel
import com.lkorasik.ktistaclient.ui.models.SettingsModel

object ConvertProfile: Converter<ProfileResponseDTO, ProfileModel> {
    override fun convert(input: ProfileResponseDTO): ProfileModel {
        val profileModel = ProfileModel()

        if(!input.image.isNullOrEmpty()) {
            val bytes = Base64.decode(input.image, Base64.DEFAULT)
            val bmp = ImageHelper.convertToBitmap(bytes)

            profileModel.image = bmp
        }

        profileModel.username = input.username
        profileModel.followers = input.followers.toString()
        profileModel.followings = input.followings.toString()

        return profileModel
    }
}