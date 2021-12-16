package com.lkorasik.ktistaclient.ui.feed.addPost

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.core.content.FileProvider
import com.lkorasik.ktistaclient.BuildConfig
import com.lkorasik.ktistaclient.ImageHelper
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityAddPostBinding


class AddPostActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var description: EditText

    private lateinit var imagePath: String

    private lateinit var binding: ActivityAddPostBinding

    private lateinit var imageHelper: ImageHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imageHelper = ImageHelper(this)

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
        imageHelper.createEmptyImageFile()?.let {
            imagePath = it.absolutePath.toString()
            val intent = imageHelper.dispatchTakePictureIntent(it)
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == RESULT_OK) {
                    image.setImageBitmap(imageHelper.createBitmap(imagePath, image.width, image.height))
                }
                1 -> if ((resultCode == RESULT_OK) && (data != null)) {
                    image.setImageURI(data.data)
                }
            }
            binding.tvTextStub.visibility = View.GONE
        }
    }

    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Exit")
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setItems(optionsMenu) { dialogInterface, i ->
            when {
                optionsMenu[i] == "Take Photo" -> {
                    dispatchTakePictureIntent()
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
