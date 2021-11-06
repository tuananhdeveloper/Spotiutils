package com.tuananh.app.spotiutils.ui.top.page.tracks

import com.tuananh.app.spotiutils.base.BaseView
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track

interface TopTracksContract {

    interface View: BaseView {
        fun showTopTracks(topTracks: List<Track>)
        fun showTopTracksLoading()
        fun hideTopTracksLoading()
    }

    interface Presenter {
        fun getTopTracks()
    }
}