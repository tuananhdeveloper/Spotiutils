package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.AudioFeaturesModelConst
import org.json.JSONObject

data class AudioFeatures(
    val acousticness: Double,
    val liveness: Double,
    val danceability: Double,
    val energy: Double,
    val speechiness: Double,
    val instrumentalness: Double,
    val valence:Double,
    val key: Int,
    val tempo: Double
) {
    constructor(jsonObject: JSONObject): this(
        jsonObject.getDouble(AudioFeaturesModelConst.ACOUSTICNESS),
        jsonObject.getDouble(AudioFeaturesModelConst.LIVENESS),
        jsonObject.getDouble(AudioFeaturesModelConst.DANCEABILITY),
        jsonObject.getDouble(AudioFeaturesModelConst.ENERGY),
        jsonObject.getDouble(AudioFeaturesModelConst.SPEECHINESS),
        jsonObject.getDouble(AudioFeaturesModelConst.INSTRUMENTALNESS),
        jsonObject.getDouble(AudioFeaturesModelConst.VALENCE),
        jsonObject.getInt(AudioFeaturesModelConst.KEY),
        jsonObject.getDouble(AudioFeaturesModelConst.TEMPO)
    )
}
