package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.TokenDataConst
import org.json.JSONObject
import java.io.Serializable
import java.util.*

data class TokenData(
    val accessToken: String,
    val tokenType: String,
    val scope: String,
    val expireAt: Date,
    val refreshToken: String
): Serializable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(TokenDataConst.ACCESS_TOKEN),
        jsonObject.getString(TokenDataConst.TOKEN_TYPE),
        jsonObject.getString(TokenDataConst.SCOPE),
        Date(jsonObject.getString(TokenDataConst.EXPIRES_IN)
            .toInt()*1000 + System.currentTimeMillis()),
        jsonObject.getString(TokenDataConst.REFRESH_TOKEN)
    )

}

