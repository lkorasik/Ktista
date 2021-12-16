package com.lkorasik.ktistaclient.ui.feed.addPost

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.lkorasik.ktistaclient.ui.helper.ImageCaptureTypes
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityAddPostBinding


class AddPostActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var description: EditText

    private lateinit var imagePath: String

    private lateinit var binding: ActivityAddPostBinding

    private var imageHelper = ImageHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolBar()

        image = binding.ivPostPhoto
        description = binding.etDescription
        binding.tvTextStub.setOnClickListener {
            chooseImage(this)
        }

        binding.tvTextStub.apply {
            startColor = getColor(R.color.color_4)
            endColor = getColor(R.color.color_1)
            invalidate()
        }
    }

    private fun dispatchTakePictureIntent() {
        imageHelper.createEmptyImageFile(this)?.let {
            imagePath = it.absolutePath.toString()
            val intent = imageHelper.createTakePictureIntent(this, it)
            startActivityForResult(intent, ImageCaptureTypes.CAMERA.ordinal)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                ImageCaptureTypes.CAMERA.ordinal -> if (resultCode == RESULT_OK) {
                    image.setImageBitmap(imageHelper.loadBitmap(imagePath, image.width, image.height))
                }
                ImageCaptureTypes.GALLERY.ordinal -> if ((resultCode == RESULT_OK) && (data != null)) {
                    image.setImageURI(data.data)
                }
            }
            binding.tvTextStub.visibility = View.GONE
        }
    }

    private fun chooseImage(context: Context) {
        val takePhoto = getString(R.string.dialog_take_photo)
        val selectPhoto = getString(R.string.dialog_select_photo)
        val exit = getString(R.string.dialog_exit)

        val optionsMenu = arrayOf(takePhoto, selectPhoto, exit)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setItems(optionsMenu) { dialogInterface, i ->
            when(optionsMenu[i]) {
                takePhoto -> dispatchTakePictureIntent()
                selectPhoto -> {
                    val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(pickPhoto, 1)
                }
                exit -> dialogInterface.dismiss()
            }
        }
        builder.show()
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
