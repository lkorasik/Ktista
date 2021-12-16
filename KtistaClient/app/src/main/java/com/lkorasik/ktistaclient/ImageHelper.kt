package com.lkorasik.ktistaclient

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.icu.text.DateFormat
import android.os.Environment
import java.io.File
import java.util.*
import kotlin.math.max
import kotlin.math.min

class ImageHelper {
    fun createImageFile(context: Context): File {
        val timeStamp = DateFormat.getDateTimeInstance().format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    fun createBitmap(path: String, viewWidth: Int, viewHeight: Int): Bitmap {
        val bmOptions = BitmapFactory.Options().apply {
            inSampleSize = max(1, min(outWidth / viewWidth, outHeight / viewHeight))
        }

        val bitmap = BitmapFactory.decodeFile(path, bmOptions)

        return rotateImage(bitmap, 90f)
    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix().apply {
            postRotate(angle)
        }
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }
}