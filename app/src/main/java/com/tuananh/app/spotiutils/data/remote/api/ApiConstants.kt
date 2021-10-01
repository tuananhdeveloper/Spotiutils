package com.tuananh.app.spotiutils.data.remote.api

object ApiConstants {

    const val SCHEME_HTTPS = "https"
    const val AUTHORITY_SPOTIFY_API = "accounts.spotify.com"
    const val V1 = "v1"

    object RequestToken {
        const val END_POINT = "api/token"
        const val GRANT_TYPE = "grant_type"
        const val CODE = "code"
        const val REDIRECT_URI = "redirect_uri"
    }

    object LastPlayedTrack {
        const val END_POINT = "me/player/recently-played"
        const val LIMIT = "limit"
    }

    object Status {
        const val CODE_200 = 200 //OK
        const val CODE_401 = 401 //Unauthorized
    }
}