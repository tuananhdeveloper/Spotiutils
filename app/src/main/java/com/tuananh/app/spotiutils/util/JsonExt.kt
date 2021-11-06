package com.tuananh.app.spotiutils.util

import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.remote.model.*
import com.tuananh.app.spotiutils.data.remote.model.Followers
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

inline fun <reified T> String.parseObjects() = JSONArray(this).run {
    MutableList(length()) { index ->
        when(T::class) {
            Album::class -> Album(getJSONObject(index)) as T
            Artist::class -> Artist(getJSONObject(index)) as T
            Image::class -> Image(getJSONObject(index)) as T
            Track::class -> Track(getJSONObject(index)) as T
            PlaylistsItem::class -> PlaylistsItem(getJSONObject(index)) as T
            RecentlyPlayedTrack::class -> RecentlyPlayedTrack(getJSONObject(index)) as T
            else -> throw JSONException("JSON EXCEPTION")
        }
    }
}

fun JSONObject.optNullableString(name: String, fallback: String? = null) : String? {
    return if (this.has(name) && !this.isNull(name)) {
        this.getString(name)
    } else {
        fallback
    }
}

fun JSONObject.optNullableInt(name: String, fallback: Int? = null) : Int? {
    return if (this.has(name) && !this.isNull(name)) {
        this.getInt(name)
    } else {
        fallback
    }
}

inline fun <reified T> JSONObject.optNullableObject(name: String, fallback: T?= null): T? {
    return if (this.has(name) && !this.isNull(name)) {
        when(T::class) {
            Album::class -> Album(getJSONObject(name)) as T
            Artist::class -> Artist(getJSONObject(name)) as T
            Image::class -> Image(getJSONObject(name)) as T
            Track::class -> Track(getJSONObject(name)) as T
            Followers::class -> Followers(getJSONObject(name)) as T
            PlaylistsItem::class -> PlaylistsItem(getJSONObject(name)) as T
            RecentlyPlayedTrack::class -> RecentlyPlayedTrack(getJSONObject(name)) as T
            else -> throw JSONException("JSON EXCEPTION")
        }
    } else {
        fallback
    }
}

