package com.tuananh.app.spotiutils.ui.top.page.artists

import com.tuananh.app.spotiutils.base.BaseView
import com.tuananh.app.spotiutils.data.remote.model.Artist

interface TopArtistsContract {

    interface View: BaseView {
        fun showTopArtists(topArtists: List<Artist>)
        fun showTopArtistsLoading()
        fun hideTopArtistsLoading()
    }

    interface Presenter {
        fun getTopArtists()
    }
}