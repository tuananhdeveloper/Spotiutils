package com.tuananh.app.spotiutils.ui.top.page.artists

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuananh.app.spotiutils.base.BaseFragment
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.repository.TopArtistsRepository
import com.tuananh.app.spotiutils.data.remote.repository.TopTracksRepository
import com.tuananh.app.spotiutils.data.remote.source.impl.TopArtistsSourceImpl
import com.tuananh.app.spotiutils.data.remote.source.impl.TopTracksSourceImpl
import com.tuananh.app.spotiutils.databinding.FragmentTopArtistsBinding
import com.tuananh.app.spotiutils.ui.top.page.artists.adapter.ArtistsPageAdapter
import com.tuananh.app.spotiutils.ui.top.page.tracks.TopTracksContract
import com.tuananh.app.spotiutils.ui.top.page.tracks.TopTracksPresenter
import com.tuananh.app.spotiutils.ui.top.page.tracks.adapter.TracksPageAdapter
import com.tuananh.app.spotiutils.ui.webview.WebViewActivity

class ArtistsPageFragment: BaseFragment<FragmentTopArtistsBinding>(), TopArtistsContract.View {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTopArtistsBinding
        get() = FragmentTopArtistsBinding::inflate

    private val adapter by lazy {
        ArtistsPageAdapter(
            mutableListOf(),
            this::onItemClickListener
        )
    }

    override fun initData() {
        TopArtistsPresenter(this,
            TopArtistsRepository.getInstance(TopArtistsSourceImpl.getInstance()))
            .getTopArtists()
    }

    override fun initView() {
        viewBinding?.run {
            recyclerviewTopArtists.layoutManager = LinearLayoutManager(context)
            recyclerviewTopArtists.adapter = adapter
        }
    }

    override fun showTopArtists(topArtists: List<Artist>) {
        adapter.updateData(topArtists as MutableList<Artist>)
    }

    override fun showTopArtistsLoading() {
        viewBinding?.loadingTopArtists?.show()
    }

    override fun hideTopArtistsLoading() {
        viewBinding?.loadingTopArtists?.hide()
    }

    override fun showMessage(messageRes: Int) {
        Toast.makeText(context, getString(messageRes), Toast.LENGTH_SHORT).show()
    }

    override fun openWebView() {
        startActivity(Intent(context, WebViewActivity::class.java))
    }

    private fun onItemClickListener(artist: Artist) {

    }
}