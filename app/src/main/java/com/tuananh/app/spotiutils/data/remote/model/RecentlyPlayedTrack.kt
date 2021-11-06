package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.RecentlyPlayedTrackModelConst
import com.tuananh.app.spotiutils.util.TrackModelConst
import com.tuananh.app.spotiutils.util.toTimeStamp
import org.json.JSONObject
import java.sql.Timestamp

data class RecentlyPlayedTrack(
    val track: Track,
    val playedAt: Timestamp
) {
    constructor(jsonObject: JSONObject): this(
        track = Track(jsonObject.getJSONObject(RecentlyPlayedTrackModelConst.TRACK)),
        playedAt = jsonObject.getString(RecentlyPlayedTrackModelConst.PLAYED_AT).toTimeStamp()
    )
}