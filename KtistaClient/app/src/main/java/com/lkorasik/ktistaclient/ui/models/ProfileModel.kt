package com.lkorasik.ktistaclient.ui.models

import android.graphics.Bitmap

data class ProfileModel(
    var image: Bitmap,
    var username: String = "",
    var followers: String = "",
    var followings: String = ""
)
