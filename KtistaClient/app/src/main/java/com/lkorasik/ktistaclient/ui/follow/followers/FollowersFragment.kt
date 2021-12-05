package com.lkorasik.ktistaclient.ui.follow.followers

import androidx.fragment.app.activityViewModels
import com.lkorasik.ktistaclient.ui.follow.AbstractFollowFragment


class FollowersFragment : AbstractFollowFragment() {

    private val viewModel: FollowersViewModel by activityViewModels()

    override fun setViewModel() {
        viewModel.followersData.observe(viewLifecycleOwner) { followers ->
            followingAdapter.setItems(followers)

        }
    }
}