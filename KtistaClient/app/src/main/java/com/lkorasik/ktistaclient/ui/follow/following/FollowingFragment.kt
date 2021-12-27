package com.lkorasik.ktistaclient.ui.follow.following

import androidx.fragment.app.activityViewModels
import com.lkorasik.ktistaclient.ui.follow.AbstractFollowFragment
import com.lkorasik.ktistaclient.ui.follow.followers.FollowersViewModel


class FollowingFragment : AbstractFollowFragment() {
    private val viewModel: FollowingViewModel by activityViewModels()

    override fun setViewModel() {
        viewModel.followingData.observe(viewLifecycleOwner) { followers ->
            followingAdapter.setItems(followers)

        }
    }
}