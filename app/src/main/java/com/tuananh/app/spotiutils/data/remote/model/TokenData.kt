package com.tuananh.app.spotiutils.data.remote.model

import com.tuananh.app.spotiutils.util.TokenDataConst
import org.json.JSONObject
import java.io.Serializable

data class TokenData(
    val accessToken: String,
    val tokenType: String,
    val scope: String,
    val expiresIn: String,
    val refreshToken: String
): Serializable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(TokenDataConst.ACCESS_TOKEN),
        jsonObject.getString(TokenDataConst.TOKEN_TYPE),
        jsonObject.getString(TokenDataConst.SCOPE),
        jsonObject.getString(TokenDataConst.EXPIRES_IN),
        jsonObject.getString(TokenDataConst.REFRESH_TOKEN)
    )

}

