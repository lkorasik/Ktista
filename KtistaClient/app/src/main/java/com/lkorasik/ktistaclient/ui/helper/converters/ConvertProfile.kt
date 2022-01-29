package com.lkorasik.ktistaclient.ui.helper.converters

import android.util.Base64
import com.lkorasik.ktistaclient.net.model.dto.ProfileResponseDTO
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.ui.models.ProfileModel

object ConvertProfile: Converter<ProfileResponseDTO, ProfileModel> {
    override fun convert(input: ProfileResponseDTO): ProfileModel {
        val result = ProfileModel()

        if(!input.image.isNullOrEmpty()) {
            val bytes = Base64.decode(input.image, Base64.DEFAULT)
            val bmp = ImageHelper.convertToBitmap(bytes)

            result.image = bmp
        }

        result.username = input.username
        result.followers = input.followers.toString()
        result.followings = input.followings.toString()

        return result
    }
}