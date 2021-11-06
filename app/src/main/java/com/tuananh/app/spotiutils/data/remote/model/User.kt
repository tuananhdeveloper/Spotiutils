package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.UserModelConst
import org.json.JSONObject

data class User(
    val country: String,
    val displayName: String,
    val email: String,
    val id: String
) {
    constructor(jsonObject: JSONObject): this(
        jsonObject.getString(UserModelConst.COUNTRY),
        jsonObject.getString(UserModelConst.DISPLAY_NAME),
        jsonObject.getString(UserModelConst.EMAIL),
        jsonObject.getString(UserModelConst.ID),
    )
}
