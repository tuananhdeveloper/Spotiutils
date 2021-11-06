package com.tuananh.app.spotiutils.ui.track

import android.util.Log
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.AudioFeatures
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.repository.TrackRepository
import com.tuananh.app.spotiutils.util.OnDataCallback

class TrackPresenter(
    private val view: TrackContract.View,
    private val trackRepository: TrackRepository
): TrackContract.Presenter {

    private var numResponse = 0

    override fun getArtist(ids: List<String>) {
        view.showLoading()
        view.hideAllView()
        trackRepository.getArtist(ids, object: OnDataCallback<List<Artist>>{
            override fun onResponse(data: List<Artist>) {
                view.showArtist(data)
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

    override fun getTopTracks(id: String) {
        view.showLoading()
        view.hideAllView()

        trackRepository.getTopTracks(id, object: OnDataCallback<List<Track>> {
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

    override fun getAudioFeatures(id: String) {
        view.showLoading()
        view.hideAllView()

        trackRepository.getAudioFeatures(id, object: OnDataCallback<AudioFeatures> {
            override fun onResponse(data: AudioFeatures) {
                view.showAudioFeatures(data)
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
        if (numResponse == NUM_REQUEST) {
            view.hideLoading()
            view.showAllView()
        }
    }
    companion object {
        const val NUM_REQUEST = 2
    }
}