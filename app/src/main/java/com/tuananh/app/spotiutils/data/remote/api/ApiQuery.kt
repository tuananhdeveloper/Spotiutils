package com.tuananh.app.spotiutils.data.remote.api

import android.net.Uri

object ApiQuery {

    private fun buildBaseUri(): Uri.Builder {
        return Uri.Builder()
            .scheme(ApiConstants.SCHEME_HTTPS)
            .authority(ApiConstants.AUTHORITY_SPOTIFY_API)
    }

    fun queryToken(grant_type: String, code: String, redirect_uri: String) = buildBaseUri()
        .appendEncodedPath(ApiConstants.RequestToken.END_POINT)
        .appendQueryParameter(ApiConstants.RequestToken.GRANT_TYPE, grant_type)
        .appendQueryParameter(ApiConstants.RequestToken.CODE, code)
        .appendQueryParameter(ApiConstants.RequestToken.REDIRECT_URI, redirect_uri)
        .toString()

    fun queryLastPlayedTrack(limit: Int = 1) = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendEncodedPath(ApiConstants.LastPlayedTrack.END_POINT)
        .appendQueryParameter(ApiConstants.LastPlayedTrack.LIMIT, limit.toString())
        .toString()

}