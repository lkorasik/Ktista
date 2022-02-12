package com.lkorasik.ktistaclient.ui.helper.converters

import android.graphics.Bitmap
import android.util.Base64
import com.lkorasik.ktistaclient.net.model.dto.AuthorDTO
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.ui.models.UserModel

object ConvertAuthor: Converter<AuthorDTO, UserModel> {
    override fun convert(input: AuthorDTO): UserModel {
        var bmp: Bitmap? = null
        if(!input.image.isNullOrEmpty()) {
            val bytes = Base64.decode(input.image, Base64.DEFAULT)
            bmp = ImageHelper.convertToBitmap(bytes)
        }

        return UserModel(
            avatar = bmp,
            username = input.username
        )
    }
}