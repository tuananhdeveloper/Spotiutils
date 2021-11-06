package com.tuananh.app.spotiutils.data.remote.model

import android.os.Parcelable
import com.tuananh.app.spotiutils.util.ArtistModelConst
import com.tuananh.app.spotiutils.util.Followers
import com.tuananh.app.spotiutils.util.optNullableString
import com.tuananh.app.spotiutils.util.parseObjects
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Followers(
    val href: String,
    val total: Int
): Parcelable {

    constructor(jsonObject: JSONObject): this(
        jsonObject.getString(Followers.HREF),
        jsonObject.getInt(Followers.TOTAL),
    )
}
