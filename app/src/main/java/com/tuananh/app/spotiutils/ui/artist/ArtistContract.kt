package com.tuananh.app.spotiutils.ui.artist

import com.tuananh.app.spotiutils.base.BaseView
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track

interface ArtistContract {
    interface View: BaseView {
        fun showTopTracks(tracks: List<Track>)
        fun showRelatedArtists(artists: List<Artist>)
        fun showLoading()
        fun hideLoading()

        fun showAllView()
        fun hideAllView()
    }

    interface Presenter {
        fun getTopTracks(id: String)
        fun getRelatedArtists(id: String)
    }
}