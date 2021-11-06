package com.tuananh.app.spotiutils.data.remote.model

import android.os.Parcelable
import com.tuananh.app.spotiutils.util.TrackModelConst
import com.tuananh.app.spotiutils.util.parseObjects
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Track(
    val album: Album,
    val artists: List<Artist>,
    val discNumber: Int,
    val durationMs: Int,
    val explicit: Boolean,
    val href: String,
    val id: String,
    val isLocal: Boolean,
    val name: String,
    val popularity: Int,
    val trackNumber: Int,
    val type: String,
    val uri: String,
) : Parcelable {

    constructor(jsonObject: JSONObject) : this(
        album = Album(jsonObject.getJSONObject(TrackModelConst.ALBUM)),
        artists = jsonObject.getString(TrackModelConst.ARTISTS).parseObjects<Artist>(),
        discNumber = jsonObject.getInt(TrackModelConst.DISC_NUMBER),
        durationMs = jsonObject.getInt(TrackModelConst.DURATION_MS),
        explicit = jsonObject.getBoolean(TrackModelConst.EXPLICIT),
        href = jsonObject.getString(TrackModelConst.HREF),
        id = jsonObject.getString(TrackModelConst.ID),
        isLocal = jsonObject.getBoolean(TrackModelConst.IS_LOCAL),
        name = jsonObject.getString(TrackModelConst.NAME),
        popularity = jsonObject.getInt(TrackModelConst.POPULARITY),
        trackNumber = jsonObject.getInt(TrackModelConst.TRACK_NUMBER),
        type = jsonObject.getString(TrackModelConst.TYPE),
        uri = jsonObject.getString(TrackModelConst.URI),
    )
}
