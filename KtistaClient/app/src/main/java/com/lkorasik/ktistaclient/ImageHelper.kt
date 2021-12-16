package com.lkorasik.ktistaclient

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.icu.text.DateFormat
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.math.max
import kotlin.math.min

class ImageHelper {
    fun createEmptyImageFile(context: Context): File? {
        val timeStamp = DateFormat.getDateTimeInstance().format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return try {
            File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
        } catch (ex: IOException) {
            Log.e(this::class.java.canonicalName, "I Cant create a temp file")
            null
        }
    }

    fun createTakePictureIntent(activity: Activity, tempFile: File): Intent {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            resolveActivity(activity.packageManager)
        }

        val photoURI: Uri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID, tempFile)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        return intent
    }

    fun loadBitmap(path: String, viewWidth: Int, viewHeight: Int): Bitmap {
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