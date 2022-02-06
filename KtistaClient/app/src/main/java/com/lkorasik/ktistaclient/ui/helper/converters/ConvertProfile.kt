package com.lkorasik.ktistaclient.ui.helper.converters

import android.graphics.Bitmap
import android.util.Base64
import com.lkorasik.ktistaclient.net.model.dto.ProfileResponseDTO
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.ui.models.ProfileModel

object ConvertProfile: Converter<ProfileResponseDTO, ProfileModel> {
    override fun convert(input: ProfileResponseDTO): ProfileModel {
        lateinit var bmp: Bitmap
        if(!input.image.isNullOrEmpty()) {
            val bytes = Base64.decode(input.image, Base64.DEFAULT)
            bmp = ImageHelper.convertToBitmap(bytes)
        }

        return ProfileModel(
            image = bmp,
            username = input.username,
            followers = input.followers.toString(),
            followings = input.followings.toString()
        )
    }
}