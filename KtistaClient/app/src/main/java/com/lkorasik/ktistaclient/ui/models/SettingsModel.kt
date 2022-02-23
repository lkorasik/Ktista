package com.lkorasik.ktistaclient.ui.models

import android.graphics.Bitmap

data class SettingsModel(
    var avatar: Bitmap? = null,
    var username: String = "",
    var email: String = ""
)
