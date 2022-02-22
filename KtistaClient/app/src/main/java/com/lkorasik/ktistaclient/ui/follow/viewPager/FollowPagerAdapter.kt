package com.lkorasik.ktistaclient.ui.follow.viewPager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FollowPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    private val listFragment = mutableListOf<Fragment>()

    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }

    fun addFragments(fragment: Fragment){
        listFragment.add(fragment)
    }
}
