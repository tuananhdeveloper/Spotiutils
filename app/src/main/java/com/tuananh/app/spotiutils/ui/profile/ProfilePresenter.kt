package com.tuananh.app.spotiutils.ui.profile

import android.util.Log
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.model.User
import com.tuananh.app.spotiutils.data.remote.repository.CurrentUserProfileRepository
import com.tuananh.app.spotiutils.data.remote.repository.RecentlyPlayedTrackRepository
import com.tuananh.app.spotiutils.ui.overview.OverviewPresenter
import com.tuananh.app.spotiutils.util.OnDataCallback

class ProfilePresenter(
    private val view: ProfileContract.View,
    private val userProfileRepository: CurrentUserProfileRepository,
    private val recentlyPlayedTrackRepository: RecentlyPlayedTrackRepository

): ProfileContract.Presenter {
    override fun getRecentlyPlayedTracks() {
        recentlyPlayedTrackRepository.getRecentlyPlayedTracks(OverviewPresenter.RECENTLY_ITEMS_LIMIT, object :
            OnDataCallback<List<RecentlyPlayedTrack>> {
            override fun onResponse(data: List<RecentlyPlayedTrack>) {
                view.hideRecentlyPlayedLoading()
                view.showRecentlyPlayedTrack(data as MutableList<RecentlyPlayedTrack>)
            }

            override fun onFailure(t: Throwable?) {
                t?.message?.let {
                    when(it) {
                        R.string.error_refresh_token_expired.toString() -> view.openWebView()
                    }
                    Log.e("my_log_exception", it)
                }
                view.hideRecentlyPlayedLoading()
                view.showMessage(R.string.error_recently_played_track)
            }
        })
    }

    override fun getUserInfo() {
        userProfileRepository.getCurrentUserProfile(object: OnDataCallback<User> {
            override fun onResponse(data: User) {
                view.showUserInfo(data)
            }

            override fun onFailure(t: Throwable?) {
                t?.message?.let {
                    when(it) {
                        R.string.error_refresh_token_expired.toString() -> view.openWebView()
                    }
                    Log.e("my_log_exception", it)
                }
                view.showMessage(R.string.error_user_info)            }
        })
    }

}