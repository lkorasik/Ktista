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
import com.lkorasik.ktistaclient.ui.helper.ImageSources
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.ActivityAddPostBinding
import com.lkorasik.ktistaclient.net.model.dto.CreatePostDTO
import com.lkorasik.ktistaclient.net.requests.CreatePostRequest


class AddPostActivity : AppCompatActivity() {
    private var image: ImageView? = null
    private var description: EditText? = null

    private var imagePath: String? = null

    private var binding: ActivityAddPostBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setToolBar()

        image = binding?.ivPostPhoto
        description = binding?.etDescription
        binding?.tvTextStub?.setOnClickListener {
            chooseImage(this)
        }

        binding?.tvTextStub?.apply {
            startColor = getColor(R.color.color_4)
            endColor = getColor(R.color.color_1)
            invalidate()
        }
    }

    private fun sendTakePictureIntent() {
        ImageHelper.createEmptyImageFile(this)?.let {
            imagePath = it.absolutePath.toString()
            val intent = ImageHelper.createTakePictureIntent(this, it)
            startActivityForResult(intent, ImageSources.CAMERA.ordinal)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                ImageSources.CAMERA.ordinal -> if (resultCode == RESULT_OK) {
                    image?.setImageBitmap(ImageHelper.loadBitmap(imagePath!!, image?.width ?: 0, image?.height ?: 0))
                }
                ImageSources.GALLERY.ordinal -> if ((resultCode == RESULT_OK) && (data != null)) {
                    image?.setImageURI(data.data)
                }
            }
            binding?.tvTextStub?.visibility = View.GONE
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
                takePhoto -> sendTakePictureIntent()
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
        if (image?.drawable == null) {
            showEmptyPostDataToast()
        } else {
            CreatePostRequest().createPost(CreatePostDTO(1, "hi", byteArrayOf(1, 2, 3)))
        }
    }

    private fun setToolBar() {
        setSupportActionBar(binding?.includeAddPostToolbar?.customToolbar)
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
