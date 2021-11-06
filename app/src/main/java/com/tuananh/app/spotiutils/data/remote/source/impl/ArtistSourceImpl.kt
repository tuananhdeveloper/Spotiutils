package com.tuananh.app.spotiutils.data.remote.source.impl

import com.tuananh.app.spotiutils.data.remote.api.ApiConstants
import com.tuananh.app.spotiutils.data.remote.api.ApiQuery
import com.tuananh.app.spotiutils.data.remote.api.RequestApi
import com.tuananh.app.spotiutils.data.remote.model.Artist
import com.tuananh.app.spotiutils.data.remote.model.Track
import com.tuananh.app.spotiutils.data.remote.source.ArtistSource
import com.tuananh.app.spotiutils.data.remote.source.DetailedArtistSource
import com.tuananh.app.spotiutils.util.BaseConst
import com.tuananh.app.spotiutils.util.MyAsyncTask
import com.tuananh.app.spotiutils.util.OnDataCallback
import com.tuananh.app.spotiutils.util.parseObjects
import org.json.JSONObject

class ArtistSourceImpl private constructor(): ArtistSource, DetailedArtistSource {

    override fun getTopTracks(id: String, callback: OnDataCallback<List<Track>>) {
        MyAsyncTask<Unit, List<Track>>(callback) {
            getTopTracks(id)
        }.execute()
    }

    override fun getArtist(ids: List<String>, callback: OnDataCallback<List<Artist>>) {
        MyAsyncTask<Unit, List<Artist>>(callback) {
            getArtist(ids)
        }.execute()
    }

    override fun getRelatedArtists(id: String, callback: OnDataCallback<List<Artist>>) {
        MyAsyncTask<Unit, List<Artist>>(callback) {
            getRelatedArtists(id)
        }.execute()
    }

    private fun getRelatedArtists(id: String): List<Artist>? {
        val response = RequestApi.doGet(ApiQuery.queryRelatedArtists(id))
        if (response != null) {
            if (response.statusCode == ApiConstants.Status.CODE_200) {
                val jsonObject = JSONObject(response.responseBody)
                return jsonObject.getString(BaseConst.ARTISTS).parseObjects()
            }
        }
        return null
    }

    private fun getArtist(ids: List<String>): List<Artist>? {
        val response = RequestApi.doGet(ApiQuery.queryArtist(ids))
        if (response != null) {
            if (response.statusCode == ApiConstants.Status.CODE_200) {
                val jsonObject = JSONObject(response.responseBody)
                return jsonObject.getString(BaseConst.ARTISTS).parseObjects()
            }
        }
        return null
    }

    private fun getTopTracks(id: String): List<Track>? {
        val response = RequestApi.doGet(ApiQuery.queryTopTrack(id))
        if (response != null) {
            if (response.statusCode == ApiConstants.Status.CODE_200) {
                val jsonObject = JSONObject(response.responseBody)
                return jsonObject.getString(BaseConst.TRACKS).parseObjects()
            }
        }
        return null
    }

    companion object {
        private var instance: ArtistSourceImpl? = null
        fun getInstance() = instance ?: ArtistSourceImpl().also { instance = it }
    }
}