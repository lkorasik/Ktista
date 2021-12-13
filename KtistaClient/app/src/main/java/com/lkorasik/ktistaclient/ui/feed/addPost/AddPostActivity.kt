package com.lkorasik.ktistaclient.ui.feed.addPost

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.icu.text.DateFormat.getDateTimeInstance
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.lkorasik.ktistaclient.BuildConfig
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityAddPostBinding
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.math.max
import kotlin.math.min


class AddPostActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var description: EditText

    private lateinit var currentPhotoPath: String

    private lateinit var binding: ActivityAddPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolBar()

        image = binding.ivPostPhoto
        description = binding.etDescription
        binding.tvTextStub.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.tvTextStub.apply {
            startColor = getColor(R.color.color_4)
            endColor = getColor(R.color.color_1)
            invalidate()
        }
    }

    private fun createImageFile(): File {
        val timeStamp = getDateTimeInstance().format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }

                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, it)
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, 0)
                }
            }
        }
    }

    private fun setPic() {
        val bmOptions = BitmapFactory.Options().apply {
            inJustDecodeBounds = true

            BitmapFactory.decodeFile(currentPhotoPath, this)

            val scaleFactor: Int = max(1, min(outWidth / image.width, outHeight / image.height))

            inJustDecodeBounds = false
            inSampleSize = scaleFactor
        }

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.apply {
            image.setImageBitmap(rotateImage(this, 90f))
        }
    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix().apply {
            postRotate(angle)
        }
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }

//    private fun chooseImage(context: Context) {
//        val optionsMenu = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Exit")
//        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
//        builder.setItems(optionsMenu) { dialogInterface, i ->
//            when {
//                optionsMenu[i] == "Take Photo" -> {
//                    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                    startActivityForResult(takePicture, 0)
//                }
//                optionsMenu[i] == "Choose from Gallery" -> {
//                    val pickPhoto =
//                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//                    startActivityForResult(pickPhoto, 1)
//                }
//                optionsMenu[i] == "Exit" -> {
//                    dialogInterface.dismiss()
//                }
//            }
//        }
//        builder.show()
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == RESULT_OK) {
                    setPic()
                }
//                1 -> if ((resultCode == RESULT_OK) && (data != null)) {
//                    val selectedImage: Uri? = data.data
//                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//                    if (selectedImage != null) {
//                        val cursor: Cursor? = contentResolver.query(selectedImage, filePathColumn, null, null, null)
//                        if (cursor != null) {
//                            cursor.moveToFirst()
//                            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
//                            val picturePath: String = cursor.getString(columnIndex)
//                            image.setImageBitmap(BitmapFactory.decodeFile(picturePath))
//                            cursor.close()
//                        }
//                    }
//                }
            }
            binding.tvTextStub.visibility = View.GONE
        }
    }

    private fun showEmptyPostDataToast() {
        val text = "Your beautiful image hasn't loaded"
        val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun sendTHISSHIT() {
        if (image.drawable == null) {
            showEmptyPostDataToast()
        } else {
            //send..........
        }
    }

    private fun setToolBar() {
        setSupportActionBar(binding.includeAddPostToolbar.customToolbar)
        supportActionBar?.title = "New post"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_add_post, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_icon_addPost -> {
                sendTHISSHIT()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
