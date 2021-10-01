package com.tuananh.app.spotiutils.data.remote.model

import org.json.JSONObject
import java.util.*

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
    val playedAt: Date
) {
    constructor(jsonObject: JSONObject) : this(

    )
}
