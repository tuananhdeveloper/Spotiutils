package com.tuananh.app.spotiutils.data.remote.repository

import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.data.remote.model.User
import com.tuananh.app.spotiutils.data.remote.source.CurrentUserProfileSource
import com.tuananh.app.spotiutils.data.remote.source.PlaylistsSource
import com.tuananh.app.spotiutils.data.remote.source.RecentlyPlayedTrackSource
import com.tuananh.app.spotiutils.util.OnDataCallback

class CurrentUserProfileRepository private constructor(
    private val currentUserProfileSource: CurrentUserProfileSource
): CurrentUserProfileSource {
    override fun getCurrentUserProfile(callback: OnDataCallback<User>) {
        currentUserProfileSource.getCurrentUserProfile(callback)
    }

    companion object {
        private var instance: CurrentUserProfileRepository? = null

        fun getInstance(currentUserProfileSource: CurrentUserProfileSource) =
            instance
                ?: CurrentUserProfileRepository(currentUserProfileSource).also { instance = it }
    }
}