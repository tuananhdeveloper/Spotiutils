package com.tuananh.app.spotiutils.ui.top.page.artists

import android.util.Log
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.repository.TopArtistsRepository
import com.tuananh.app.spotiutils.data.remote.repository.TopTracksRepository
import com.tuananh.app.spotiutils.util.OnDataCallback

class TopArtistsPresenter(
    private val view: TopArtistsContract.View,
    private val repository: TopArtistsRepository
): TopArtistsContract.Presenter {

    override fun getTopArtists() {
        view.showTopArtistsLoading()
        repository.getTopArtists(40,object: OnDataCallback<List<Artist>> {

            override fun onResponse(data: List<Artist>) {
                view.hideTopArtistsLoading()
                view.showTopArtists(data)
            }

            override fun onFailure(t: Throwable?) {
                t?.message?.let {
                    when(it) {
                        R.string.error_refresh_token_expired.toString() -> view.openWebView()
                    }
                    Log.e("my_log_exception", it)
                }
                view.hideTopArtistsLoading()
                view.showMessage(R.string.error_top_tracks)
            }
        })
    }

}