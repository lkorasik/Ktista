package com.lkorasik.ktistaclient.ui.profile

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lkorasik.ktistaclient.ui.helper.ImageSources
import com.lkorasik.ktistaclient.ui.helper.ImageHelper
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.FragmentProfileBinding
import com.lkorasik.ktistaclient.ui.post.PostsRecyclerAdapter


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by navGraphViewModels(R.id.navigation_profile)
    private lateinit var postsAdapter: PostsRecyclerAdapter
    private val binding get() = _binding!!

    private lateinit var nickname: TextView
    private lateinit var image: ImageView

    private lateinit var imagePath: String
    private var imageHelper = ImageHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postsAdapter = PostsRecyclerAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        image = binding.includedProfileInfo.ivAvatar

        nickname = binding.includedProfileInfo.profileName

        viewModel.data.observe(this, {
            nickname.text = it.username
        })

        binding.includedProfileInfo.ivAvatar.setOnClickListener {
            chooseImage(activity)
        }

        binding.includedProfileInfo.llFollowingInfo.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_followFragment, bundleOf("position" to 1))
        }

        binding.includedProfileInfo.llFollowersInfo.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_followFragment, bundleOf("position" to 0))
        }

        viewModel.getProfile()

        return binding.root
    }

    private fun sendTakePictureIntent() {
        imageHelper.createEmptyImageFile(activity!!)?.let {
            imagePath = it.absolutePath.toString()
            val intent = imageHelper.createTakePictureIntent(activity!!, it)
            startActivityForResult(intent, ImageSources.CAMERA.ordinal)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_CANCELED) {
            when (requestCode) {
                ImageSources.CAMERA.ordinal -> if (resultCode == RESULT_OK) {
                    image.setImageBitmap(imageHelper.loadBitmap(imagePath, image.width, image.height))
                }
                ImageSources.GALLERY.ordinal -> if ((resultCode == RESULT_OK) && (data != null)) {
                    image.setImageURI(data.data)
                }
            }
        }
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