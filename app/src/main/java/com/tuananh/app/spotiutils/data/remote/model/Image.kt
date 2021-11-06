package com.tuananh.app.spotiutils.data.remote.model

import android.os.Parcelable
import com.tuananh.app.spotiutils.util.ImageModelConst
import kotlinx.parcelize.Parcelize
import org.json.JSONObject

@Parcelize
data class Image(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable {
    enum class ImageType {
        SIZE640_640, SIZE300_300, SIZE64_64
    }

    constructor(jsonObject: JSONObject): this(
        jsonObject.getInt(ImageModelConst.HEIGHT),
        jsonObject.getString(ImageModelConst.URL),
        jsonObject.getInt(ImageModelConst.WIDTH)
    )
}

