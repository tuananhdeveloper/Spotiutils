package com.tuananh.app.spotiutils.ui.track

import com.tuananh.app.spotiutils.base.BaseView
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.AudioFeatures
import com.tuananh.app.spotiutils.data.remote.model.Track

interface TrackContract {

    interface View: BaseView {
        fun showTopTracks(tracks: List<Track>)
        fun showAudioFeatures(audioFeatures: AudioFeatures)
        fun showArtist(artists: List<Artist>)
        fun showLoading()
        fun hideLoading()

        fun showAllView()
        fun hideAllView()
    }

    interface Presenter {
        fun getTopTracks(id: String)
        fun getAudioFeatures(id: String)
        fun getArtist(ids: List<String>)
    }
}