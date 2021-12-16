package com.lkorasik.ktistaclient.ui.profile

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lkorasik.ktistaclient.BuildConfig
import com.lkorasik.ktistaclient.ImageHelper
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.FragmentProfileBinding
import com.lkorasik.ktistaclient.ui.post.PostsRecyclerAdapter
import java.io.File
import java.io.IOException


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by navGraphViewModels(R.id.navigation_profile)
    private lateinit var postsAdapter: PostsRecyclerAdapter
    private val binding get() = _binding!!

    private lateinit var nickname: TextView

    private lateinit var imagePath: String
    private lateinit var image: ImageView

    private var imageHelper = ImageHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postsAdapter = PostsRecyclerAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        image = binding.includedProfileInfo.ivAvatar

        viewModel.data.observe(this, {
            nickname.text = it.username
        })

        binding.includedProfileInfo.ivAvatar.setOnClickListener {
            chooseImage(activity)
            //TODO("Load avatar!")
        }

        nickname = binding.includedProfileInfo.profileName

        binding.includedProfileInfo.llFollowingInfo.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_followFragment, bundleOf("position" to 1))
        }

        binding.includedProfileInfo.llFollowersInfo.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_followFragment, bundleOf("position" to 0))
        }

        viewModel.getProfile()

        return binding.root
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            resolveActivity(activity?.packageManager!!)?.also {
                val photoFile = imageHelper.createImageFile(activity!!)

                imagePath = photoFile?.absolutePath.toString()

                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(activity!!, BuildConfig.APPLICATION_ID, it)
                    putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(this, 0)
                }
            }
        }
    }

    private fun chooseImage(context: Context?) {
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
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvPosts
        recyclerView.setHasFixedSize(true)

        viewModel.postsData.observe(viewLifecycleOwner) { posts ->
            postsAdapter.setItems(posts)
        }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}