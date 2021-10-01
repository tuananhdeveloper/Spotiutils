package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.ArtistModelConst
import org.json.JSONObject

data class Artist(
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
) {
    constructor(jsonObject: JSONObject): this(
        jsonObject.getString(ArtistModelConst.HREF),
        jsonObject.getString(ArtistModelConst.ID),
        jsonObject.getString(ArtistModelConst.NAME),
        jsonObject.getString(ArtistModelConst.TYPE),
        jsonObject.getString(ArtistModelConst.URI)
    )
}
