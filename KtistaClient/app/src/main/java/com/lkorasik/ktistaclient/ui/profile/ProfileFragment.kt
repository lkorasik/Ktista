package com.lkorasik.ktistaclient.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lkorasik.ktistaclient.MainActivity
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.FragmentProfileBinding
import com.lkorasik.ktistaclient.ui.post.PostsRecyclerAdapter


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by navGraphViewModels(R.id.navigation_profile)
    private lateinit var postsAdapter: PostsRecyclerAdapter
    private val binding get() = _binding!!
    private lateinit var rootActivity: MainActivity

    private lateinit var nickname: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postsAdapter = PostsRecyclerAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        viewModel.data.observe(this, {
            nickname.text = it.username
        })

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

