package com.tuananh.app.spotiutils.data.remote.source

import com.tuananh.app.spotiutils.data.remote.model.User
import com.tuananh.app.spotiutils.util.OnDataCallback

interface CurrentUserProfileSource {
    fun getCurrentUserProfile(callback: OnDataCallback<User>)
}