package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.PlaylistsItemTracksModelConst
import org.json.JSONObject

data class PlaylistsItemTracks(
    val href: String,
    val total: Int
) {
    constructor(jsonObject: JSONObject): this(
        jsonObject.getString(PlaylistsItemTracksModelConst.HREF),
        jsonObject.getInt(PlaylistsItemTracksModelConst.TOTAL),
    )
}
