package com.lkorasik.ktistaclient.ui.feed

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.net.model.dto.CreatePostDTO
import com.lkorasik.ktistaclient.net.requests.CreatePostRequest
import com.lkorasik.ktistaclient.ui.post.PostsRecyclerAdapter

class FeedFragment : Fragment() {

    private lateinit var postsAdapter: PostsRecyclerAdapter
    private val viewModel: FeedViewModel by navGraphViewModels(R.id.navigation_feed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        postsAdapter = PostsRecyclerAdapter()
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

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_posts)
        recyclerView.setHasFixedSize(true)

        viewModel.postsData.observe(viewLifecycleOwner) { posts ->
            postsAdapter.setItems(posts)
        }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = postsAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_feed_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_post -> {
                findNavController().navigate(R.id.action_navigation_feed_to_addPostActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
