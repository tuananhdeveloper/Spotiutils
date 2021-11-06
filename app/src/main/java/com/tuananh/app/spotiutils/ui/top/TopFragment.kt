package com.tuananh.app.spotiutils.ui.top

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.tuananh.app.spotiutils.base.BaseFragment
import com.tuananh.app.spotiutils.databinding.FragmentTopBinding
import com.tuananh.app.spotiutils.ui.top.adapter.TopPagerAdapter

class TopFragment: BaseFragment<FragmentTopBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopBinding
        get() = FragmentTopBinding::inflate

    private val topPagerAdapter by lazy {
        TopPagerAdapter(this)
    }

    override fun initView() {
        viewBinding?.run {
            myViewPager.adapter = topPagerAdapter
            TabLayoutMediator(myTabLayout, myViewPager) { tab, position ->
                if(position == 0) {
                    tab.text = TOP_TRACKS_FRAGMENT_TITLE
                }
                else {
                    tab.text = TOP_ARTISTS_FRAGMENT_TITLE
                }
            }.attach()
        }
    }

    override fun initData() {

    }

    companion object {
        const val TOP_TRACKS_FRAGMENT_TITLE = "Tracks"
        const val TOP_ARTISTS_FRAGMENT_TITLE = "Artists"
    }
}