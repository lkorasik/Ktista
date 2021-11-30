package com.lkorasik.ktistaclient.ui.profile

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.databinding.FragmentProfileBinding
import com.lkorasik.ktistaclient.ui.feed.FeedRecyclerAdapter
import com.lkorasik.ktistaclient.ui.feed.FeedViewModel
import android.widget.TextView

import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import androidx.core.text.HtmlCompat


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    private val viewModel: FeedViewModel by navGraphViewModels(R.id.navigation_feed)
    private lateinit var feedAdapter: FeedRecyclerAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val recyclerView = binding.rvPosts
        recyclerView.setHasFixedSize(true)

        viewModel.postsData.observe(viewLifecycleOwner) { posts ->
            feedAdapter.setItems(posts)
        }


        with(recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = feedAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedAdapter = FeedRecyclerAdapter()
    }
}

