package com.lkorasik.ktistaclient.ui.follow.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.lkorasik.ktistaclient.databinding.FragmentPagerFollowerBinding
import com.lkorasik.ktistaclient.ui.follow.followers.FollowersFragment
import com.lkorasik.ktistaclient.ui.follow.following.FollowingFragment


class FollowPagerFragment : Fragment() {
    private var _binding: FragmentPagerFollowerBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Try use binding before " +
            "onCreateView or after onDestroyView")

    private lateinit var followAdapter: FollowPagerAdapter
    private lateinit var viewPager: ViewPager2
    private val listNames = listOf("Followers", "Followings")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagerFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followAdapter = FollowPagerAdapter(this)
        followAdapter.addFragments(FollowingFragment())
        followAdapter.addFragments(FollowersFragment())

        viewPager = binding.vp2Follow
        viewPager.adapter = followAdapter

        val tabLayout = binding.tlFollow

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = listNames[position]
        }.attach()

        viewPager.setCurrentItem(arguments?.getInt("position") ?: 0, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
