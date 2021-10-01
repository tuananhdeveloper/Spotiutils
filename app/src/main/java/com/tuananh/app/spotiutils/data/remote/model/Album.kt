package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.AlbumModelConst
import org.json.JSONObject
import java.util.*

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
) {
    constructor(jsonObject: JSONObject): this(
        jsonObject.getString(AlbumModelConst.ALBUM_TYPE),
        jsonObject.getJSONArray()
    )
}
