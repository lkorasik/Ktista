package com.lkorasik.ktistaclient.ui

import android.app.*
import android.app.AlertDialog
import android.content.*
import android.graphics.Bitmap
import android.os.*
import android.provider.MediaStore
import android.widget.*
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityAddPostBinding
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent

import android.content.DialogInterface
import android.database.Cursor
import androidx.appcompat.app.*
import android.graphics.BitmapFactory
import android.net.Uri


class AddPostActivity: AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var description: EditText

    private var bindingObject: ActivityAddPostBinding? = null
    private val binding get() = bindingObject!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingObject = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        image = binding.ivPostPhoto
        description = binding.etDescription

        val grad = binding.tvTextStub
        grad.startColor = getColor(R.color.base_color)
        grad.endColor = getColor(R.color.gradient_end)
        grad.invalidate()

        chooseImage(this)
    }

    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Exit")
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setItems(optionsMenu) { dialogInterface, i ->
            when {
                optionsMenu[i] == "Take Photo" -> {
                    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(takePicture, 0)
                }
                optionsMenu[i] == "Choose from Gallery" -> {
                    val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(pickPhoto, 1)
                }
                optionsMenu[i] == "Exit" -> {
                    dialogInterface.dismiss()
                }
            }
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage = data.extras!!["data"] as Bitmap?
                    image.setImageBitmap(selectedImage)
                }
                1 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage: Uri? = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val cursor: Cursor? =
                            contentResolver.query(selectedImage, filePathColumn, null, null, null)
                        if (cursor != null) {
                            cursor.moveToFirst()
                            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                            val picturePath: String = cursor.getString(columnIndex)
                            image.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                            cursor.close()
                        }
                    }
                }
            }
        }
    }
}