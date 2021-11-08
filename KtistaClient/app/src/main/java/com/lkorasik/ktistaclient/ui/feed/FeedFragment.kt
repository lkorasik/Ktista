package com.lkorasik.ktistaclient.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lkorasik.ktistaclient.R

class FeedFragment : Fragment() {

    private lateinit var feedAdapter: FeedRecyclerAdapter
    private val viewModel: FeedViewModel by navGraphViewModels(R.id.navigation_feed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedAdapter = FeedRecyclerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_feed)
        recyclerView.setHasFixedSize(true)

        viewModel.postsData.observe(viewLifecycleOwner) { posts ->
            feedAdapter.setItems(posts)
        }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = feedAdapter
        }
    }
}
