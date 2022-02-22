package com.lkorasik.ktistaclient.ui.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lkorasik.ktistaclient.databinding.FragmentRecyclerFollowerBinding

abstract class AbstractFollowFragment : Fragment() {
    protected lateinit var followingAdapter: FollowRecyclerAdapter
    private var _binding: FragmentRecyclerFollowerBinding? = null
    protected val binding get() = _binding ?: throw IllegalStateException("Try use binding " +
            "before onCreateView or after onDestroyView")

    abstract fun setViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        followingAdapter = FollowRecyclerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerFollowerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvFollowing
        recyclerView.setHasFixedSize(true)

        setViewModel()

        with(recyclerView) {
            layoutManager = LinearLayoutManager(activity)
            adapter = followingAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
