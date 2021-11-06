package com.tuananh.app.spotiutils.ui.artist

import android.util.Log
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.repository.DetailedArtistRepository
import com.tuananh.app.spotiutils.ui.track.TrackPresenter
import com.tuananh.app.spotiutils.util.OnDataCallback

class ArtistPresenter(
    private val view: ArtistContract.View,
    private val detailedArtistRepository: DetailedArtistRepository
): ArtistContract.Presenter {

    private var numResponse = 0

    override fun getTopTracks(id: String) {
        view.showLoading()
        view.hideAllView()

        detailedArtistRepository.getTopTracks(id, object: OnDataCallback<List<Track>> {
            override fun onResponse(data: List<Track>) {
                view.showTopTracks(data)
                hideLoadingAndShowAllView()
            }

            override fun onFailure(t: Throwable?) {
                hideLoadingAndShowAllView()
                t?.message?.let {
                    when(it) {
                        R.string.error_refresh_token_expired.toString() -> view.openWebView()
                    }
                    Log.e("my_log_exception", it)
                }
            }
        })
    }

    override fun getRelatedArtists(id: String) {
        view.showLoading()
        view.hideAllView()

        detailedArtistRepository.getRelatedArtists(id, object: OnDataCallback<List<Artist>>{
            override fun onResponse(data: List<Artist>) {
                view.showRelatedArtists(data)
                hideLoadingAndShowAllView()
            }

            override fun onFailure(t: Throwable?) {
                hideLoadingAndShowAllView()
                t?.message?.let {
                    when(it) {
                        R.string.error_refresh_token_expired.toString() -> view.openWebView()
                    }
                    Log.e("my_log_exception", it)
                }
            }
        })
    }

    private fun hideLoadingAndShowAllView() {
        numResponse++
        if (numResponse == TrackPresenter.NUM_REQUEST) {
            view.hideLoading()
            view.showAllView()
        }
    }
}