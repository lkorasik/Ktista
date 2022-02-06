package com.lkorasik.ktistaclient.ui.models

import android.graphics.Bitmap

data class UserModel(
    var avatar: Bitmap?,
    var username: String = ""
)
