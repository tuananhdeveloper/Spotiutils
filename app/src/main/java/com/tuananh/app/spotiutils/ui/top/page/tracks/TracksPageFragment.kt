package com.tuananh.app.spotiutils.ui.top.page.tracks

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuananh.app.spotiutils.base.BaseFragment
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.repository.TopTracksRepository
import com.tuananh.app.spotiutils.data.remote.source.impl.TopTracksSourceImpl
import com.tuananh.app.spotiutils.databinding.FragmentTopTracksBinding
import com.tuananh.app.spotiutils.ui.top.page.tracks.adapter.TracksPageAdapter
import com.tuananh.app.spotiutils.ui.webview.WebViewActivity

class TracksPageFragment: BaseFragment<FragmentTopTracksBinding>(), TopTracksContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopTracksBinding
        get() = FragmentTopTracksBinding::inflate

    private val adapter by lazy {
        TracksPageAdapter(
            mutableListOf(),
            this::onItemClickListener
        )
    }

    override fun initData() {
        TopTracksPresenter(this,
        TopTracksRepository.getInstance(TopTracksSourceImpl.getInstance()))
            .getTopTracks()
    }

    override fun initView() {
        viewBinding?.run {
            recyclerviewTopTracks.layoutManager = LinearLayoutManager(context)
            recyclerviewTopTracks.adapter = adapter
        }
    }

    override fun showTopTracks(topTracks: List<Track>) {
        adapter.updateData(topTracks as MutableList<Track>)
    }

    override fun showTopTracksLoading() {
        viewBinding?.loadingTopTracks?.show()
    }

    override fun hideTopTracksLoading() {
        viewBinding?.loadingTopTracks?.hide()
    }

    override fun showMessage(messageRes: Int) {
        Toast.makeText(context, getString(messageRes), Toast.LENGTH_SHORT).show()
    }

    override fun openWebView() {
        startActivity(Intent(context, WebViewActivity::class.java))
    }

    private fun onItemClickListener(track: Track) {

    }

}