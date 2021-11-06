package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.OwnerModelConst
import org.json.JSONObject

data class Owner(
    val displayName: String,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
){
    constructor(jsonObject: JSONObject): this(
        jsonObject.getString(OwnerModelConst.DISPLAY_NAME),
        jsonObject.getString(OwnerModelConst.HREF),
        jsonObject.getString(OwnerModelConst.ID),
        jsonObject.getString(OwnerModelConst.TYPE),
        jsonObject.getString(OwnerModelConst.URI),
    )
}
