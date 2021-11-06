package com.tuananh.app.spotiutils.data.remote.model

import android.os.Parcelable
import com.tuananh.app.spotiutils.util.AlbumModelConst
import com.tuananh.app.spotiutils.util.parseObjects
import com.tuananh.app.spotiutils.util.toDate
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import java.util.*

@Parcelize
data class Album(
    val albumType: String,
    val artists: List<Artist>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val releaseDate: Date,
    val releaseDatePrecision: String,
    val totalTracks: Int,
    val type: String,
    val uri: String
) : Parcelable {
    constructor(jsonObject: JSONObject): this (
        jsonObject.getString(AlbumModelConst.ALBUM_TYPE),
        jsonObject.getString(AlbumModelConst.ARTISTS).parseObjects<Artist>(),
        jsonObject.getString(AlbumModelConst.HREF),
        jsonObject.getString(AlbumModelConst.ID),
        jsonObject.getString(AlbumModelConst.IMAGES).parseObjects<Image>(),
        jsonObject.getString(AlbumModelConst.NAME),
        jsonObject.getString(AlbumModelConst.RELEASE_DATE).toDate(AlbumModelConst.DATE_FORMAT),
        jsonObject.getString(AlbumModelConst.RELEASE_DATE_PRECISION),
        jsonObject.getInt(AlbumModelConst.TOTAL_TRACKS),
        jsonObject.getString(AlbumModelConst.TYPE),
        jsonObject.getString(AlbumModelConst.URI)
    )
}
