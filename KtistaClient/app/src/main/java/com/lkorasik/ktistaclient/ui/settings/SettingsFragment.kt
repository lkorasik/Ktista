package com.lkorasik.ktistaclient.ui.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.FragmentSettingsBinding
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.ui.helper.ImageSources

class SettingsFragment : Fragment() {
    private lateinit var settingsViewModel: SettingsViewModel
    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding ?: throw IllegalStateException("Try use binding before onCreateView or after onDestroyView")

    private var image: ImageView? = null
    private var changeAvatar: Button? = null

    private var imagePath: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.settingBtChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_settings_to_changePasswordDialog)
        }

        image = binding.ivAvatar
        image?.setOnClickListener {
            chooseImage(activity)
        }

        changeAvatar = binding.settingBtChangeAvatar
        changeAvatar?.setOnClickListener {
            chooseImage(activity)
        }

        return binding.root
    }

    private fun chooseImage(context: Context?) {
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
                    startActivityForResult(pickPhoto, ImageSources.GALLERY.ordinal)
                }
                exit -> dialogInterface.dismiss()
            }
        }
        builder.show()
    }

    private fun sendTakePictureIntent() {
        ImageHelper.createEmptyImageFile(activity!!)?.let {
            imagePath = it.absolutePath.toString()
            val intent = ImageHelper.createTakePictureIntent(activity!!, it)
            startActivityForResult(intent, ImageSources.CAMERA.ordinal)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_CANCELED) {
            when (requestCode) {
                ImageSources.CAMERA.ordinal -> if (resultCode == Activity.RESULT_OK) {
                    image?.setImageBitmap(ImageHelper.loadBitmap(imagePath!!, image?.width ?: 0, image?.height ?: 0))
                }
                ImageSources.GALLERY.ordinal -> if ((resultCode == Activity.RESULT_OK) && (data != null)) {
                    image?.setImageURI(data.data)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
