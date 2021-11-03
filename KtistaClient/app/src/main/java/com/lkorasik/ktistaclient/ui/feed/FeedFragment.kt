package com.lkorasik.ktistaclient.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.lkorasik.ktistaclient.R
import com.lkorasik.ktistaclient.models.PostModel
import com.lkorasik.ktistaclient.models.UserModel

class FeedFragment : Fragment() {

    private lateinit var feedAdapter: FeedRecyclerAdapter

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
        feedAdapter = FeedRecyclerAdapter()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = feedAdapter
        }
        feedAdapter.setItems(posts = loadPosts())
    }

    //Test function
    private fun loadPosts(): ArrayList<PostModel> {
        val usr = UserModel(
            avatar = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
            name = "Bob",
            login = "@bobik"
        )
        val photo =
            "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg"
        return arrayListOf(
            PostModel(user = usr, photo = photo, description = "342423", likeCount = "322", dislikeCount = "1337"),
            PostModel(user = usr, photo = photo, likeCount = "322", dislikeCount = "1337"),
            PostModel(user = usr, photo = photo, likeCount = "2", dislikeCount = "37"),
            PostModel(
                user = usr,
                photo = photo,
                likeCount = "32",
                dislikeCount = "17"
            )
        )

    }
}