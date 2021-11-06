package com.tuananh.app.spotiutils.ui.top.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tuananh.app.spotiutils.ui.top.page.artists.ArtistsPageFragment
import com.tuananh.app.spotiutils.ui.top.page.tracks.TracksPageFragment


class TopPagerAdapter(
    fragment: Fragment
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        if(position == 0) {
            val fragment0 = TracksPageFragment()
            return fragment0
        }
        val fragment1 = ArtistsPageFragment()
        return fragment1
    }

    companion object {
        const val ITEM_COUNT = 2
    }
}