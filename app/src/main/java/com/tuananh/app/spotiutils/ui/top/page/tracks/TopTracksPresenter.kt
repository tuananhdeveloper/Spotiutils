package com.tuananh.app.spotiutils.ui.top.page.tracks

import android.util.Log
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.repository.TopTracksRepository
import com.tuananh.app.spotiutils.util.OnDataCallback

class TopTracksPresenter(
    private val view: TopTracksContract.View,
    private val repository: TopTracksRepository
): TopTracksContract.Presenter {

    override fun getTopTracks() {
        view.showTopTracksLoading()
        repository.getTopTracks(40,object: OnDataCallback<List<Track>> {

            override fun onResponse(data: List<Track>) {
                view.hideTopTracksLoading()
                view.showTopTracks(data)
            }

            override fun onFailure(t: Throwable?) {
                t?.message?.let {
                    when(it) {
                        R.string.error_refresh_token_expired.toString() -> view.openWebView()
                    }
                    Log.e("my_log_exception", it)
                }
                view.hideTopTracksLoading()
                view.showMessage(R.string.error_top_tracks)
            }
        })
    }

}