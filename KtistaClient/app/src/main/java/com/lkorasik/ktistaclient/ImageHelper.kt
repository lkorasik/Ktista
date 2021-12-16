package com.lkorasik.ktistaclient

import android.content.Context
import android.icu.text.DateFormat
import android.os.Environment
import java.io.File
import java.util.*

class ImageHelper {
    fun createImageFile(context: Context): File {
        val timeStamp = DateFormat.getDateTimeInstance().format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }
}