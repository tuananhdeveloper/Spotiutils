package com.tuananh.app.spotiutils.data.remote.source.impl

import android.util.Log
import com.tuananh.app.spotiutils.PreparedData
import com.tuananh.app.spotiutils.data.remote.api.ApiConstants
import com.tuananh.app.spotiutils.data.remote.api.ApiQuery
import com.tuananh.app.spotiutils.data.remote.api.RequestApi
import com.tuananh.app.spotiutils.data.remote.model.PlaylistsItem
import com.tuananh.app.spotiutils.data.remote.source.PlaylistsSource
import com.tuananh.app.spotiutils.util.BaseConst
import com.tuananh.app.spotiutils.util.MyAsyncTask
import com.tuananh.app.spotiutils.util.OnDataCallback
import com.tuananh.app.spotiutils.util.parseObjects
import org.json.JSONObject

class PlaylistsSourceImpl private constructor(): PlaylistsSource {
    override fun getPlaylistsList(callback: OnDataCallback<List<PlaylistsItem>>) {
        MyAsyncTask<Unit, List<PlaylistsItem>>(callback) {
            getPlaylistsList()
        }.execute()
    }

    private fun getPlaylistsList(): List<PlaylistsItem>? {
        val response = RequestApi.doGet(ApiQuery.queryPlaylists())
        if (response != null) {
            if (response.statusCode == ApiConstants.Status.CODE_200) {
                Log.d("my_log_playlists", response.toString())

                return JSONObject(response.responseBody)
                    .getString(BaseConst.ITEMS)
                    .parseObjects()
            } else {
                return null
            }
        }
        return null
    }

    companion object {
        private var instance: PlaylistsSourceImpl? = null
        fun getInstance() = instance ?: PlaylistsSourceImpl().also { instance = it }
    }
}