package com.tuananh.app.spotiutils.data.remote.model

import android.os.Parcelable
import com.tuananh.app.spotiutils.util.*
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Artist(
    val followers: Followers?,
    val href: String,
    val id: String,
    val images: List<Image>?,
    val popularity: Int?,
    val name: String,
    val type: String,
    val uri: String
): Parcelable {
    constructor(jsonObject: JSONObject): this(
        jsonObject.optNullableObject(ArtistModelConst.FOLLOWERS),
        jsonObject.getString(ArtistModelConst.HREF),
        jsonObject.getString(ArtistModelConst.ID),
        jsonObject.optNullableString(ArtistModelConst.IMAGES)?.parseObjects<Image>(),
        jsonObject.optNullableInt(ArtistModelConst.POPULARITY),
        jsonObject.getString(ArtistModelConst.NAME),
        jsonObject.getString(ArtistModelConst.TYPE),
        jsonObject.getString(ArtistModelConst.URI)
    )

}
