package com.lkorasik.ktistaclient.ui.helper.converters

import android.util.Base64
import com.lkorasik.ktistaclient.net.model.dto.PostDTO
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.ui.models.PostModel

object ConvertPost: Converter<PostDTO, PostModel> {
    override fun convert(input: PostDTO): PostModel {
        val bytes = Base64.decode(input.data, Base64.DEFAULT)
        val bmp = ImageHelper.convertToBitmap(bytes)

        return PostModel(
            user = ConvertAuthor.convert(input.author),
            photo = bmp,
            description = input.text
        )
    }
}
