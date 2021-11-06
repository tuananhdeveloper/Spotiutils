package com.tuananh.app.spotiutils.ui.profile

import com.tuananh.app.spotiutils.base.BaseView
import com.tuananh.app.spotiutils.data.remote.model.RecentlyPlayedTrack
import com.tuananh.app.spotiutils.data.remote.model.User

interface ProfileContract {
    interface View: BaseView {
        fun showRecentlyPlayedTrack(tracks: MutableList<RecentlyPlayedTrack>)
        fun showRecentlyPlayedLoading()
        fun hideRecentlyPlayedLoading()

        fun showUserInfo(user: User)
    }

    interface Presenter {
        fun getRecentlyPlayedTracks()
        fun getUserInfo()
    }
}