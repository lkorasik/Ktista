package com.lkorasik.ktistaclient.ui

import android.content.*
import android.graphics.Bitmap
import android.os.*
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityAddPostBinding

class AddPostActivity: AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var description: EditText

    private var bindingObject: ActivityAddPostBinding? = null
    private val binding get() = bindingObject!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingObject = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        image = binding.ivImage
        description = binding.etDescription

        dispatchTakePictureIntent()

        val draw = getDrawable(R.drawable.outrun_vaporwave_hd_wallpaper_preview)
        image.setImageDrawable(draw)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            image.setImageBitmap(imageBitmap)
        }
    }

    private val REQUEST_IMAGE_CAPTURE = 1

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }
}