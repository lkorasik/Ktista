package com.lkorasik.ktistaclient.ui.feed

import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedAdapter = FeedRecyclerAdapter()
        feedAdapter.setItems(posts = loadPosts())
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

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = feedAdapter
        }
    }

    /*
    Test Function
     */
    private fun loadPosts(): ArrayList<PostModel> {
        val photo =
            "https://v1.popcornnews.ru/k2/news/1200/upload/news/414696148775.jpg"
        val text = "texttexttexttexttextte\nxttexttextte\nxttexttexttexttext"
        val usr = UserModel(
            avatar = photo,
            name = "Bob",
            login = "@bobik"
        )

        return arrayListOf(
            PostModel(
                user = usr,
                photo = photo,
                description = text,
                likeCount = "322",
                dislikeCount = "1337"
            ),
            PostModel(
                user = usr,
                photo = photo,
                likeCount = "322",
                dislikeCount = "1337",
                commentCount = "232"
            ),
            PostModel(
                user = usr,
                photo = photo,
                likeCount = "2",
                dislikeCount = "37",
                commentCount = "232"
            ),
            PostModel(
                user = usr,
                photo = photo,
                description = text,
                likeCount = "32",
                dislikeCount = "17",
                commentCount = "232"
            )
        )

    }
}