package com.tuananh.app.spotiutils.data.remote.api

import android.net.Uri

object ApiQuery {

    private fun buildBaseUri(): Uri.Builder {
        return Uri.Builder()
            .scheme(ApiConstants.SCHEME_HTTPS)
            .authority(ApiConstants.AUTHORITY_SPOTIFY_API)
    }

    private fun buildAccountBaseUri(): Uri.Builder {
        return Uri.Builder()
            .scheme(ApiConstants.SCHEME_HTTPS)
            .authority(ApiConstants.AUTHORITY_ACCOUNT)
    }

    fun queryRefreshedToken(grantType: String, refreshToken: String) = buildAccountBaseUri()
        .appendEncodedPath(ApiConstants.RefreshedToken.END_POINT)
        .appendQueryParameter(ApiConstants.RefreshedToken.GRANT_TYPE, grantType)
        .appendQueryParameter(ApiConstants.RefreshedToken.REFRESH_TOKEN, refreshToken)
        .toString()

    fun queryToken(grantType: String, code: String, redirectUri: String) = buildAccountBaseUri()
        .appendEncodedPath(ApiConstants.AccessToken.END_POINT)
        .appendQueryParameter(ApiConstants.AccessToken.GRANT_TYPE, grantType)
        .appendQueryParameter(ApiConstants.AccessToken.CODE, code)
        .appendQueryParameter(ApiConstants.AccessToken.REDIRECT_URI, redirectUri)
        .toString()

    fun queryRecentlyPlayedTrack(limit: Int = 1) = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendEncodedPath(ApiConstants.LastPlayedTrack.END_POINT)
        .appendQueryParameter(ApiConstants.LastPlayedTrack.LIMIT, limit.toString())
        .toString()

    fun queryPlaylists(limit: Int = 4) = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendEncodedPath(ApiConstants.Playlists.END_POINT)
        .appendQueryParameter(ApiConstants.LastPlayedTrack.LIMIT, limit.toString())
        .toString()

    fun queryTopTracks(limit: Int = 20, timeRange: String) = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendEncodedPath(ApiConstants.TopTrack.END_POINT)
        .appendQueryParameter(ApiConstants.TopTrack.TIME_RANGE, timeRange)
        .appendQueryParameter(ApiConstants.TopTrack.LIMIT, limit.toString())
        .toString()

    fun queryTopArtists(limit: Int = 20, timeRange: String) = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendEncodedPath(ApiConstants.TopArtist.END_POINT)
        .appendQueryParameter(ApiConstants.TopArtist.TIME_RANGE, timeRange)
        .appendQueryParameter(ApiConstants.TopArtist.LIMIT, limit.toString())
        .toString()

    fun queryCurrentUserProfile() = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendEncodedPath(ApiConstants.CurrentUserProfile.END_POINT)
        .toString()

    fun queryTrack(id: String) = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendEncodedPath(ApiConstants.Track.END_POINT)
        .appendPath(id)
        .toString()

    fun queryAudioFeatures(trackId: String) = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendEncodedPath(ApiConstants.AudioFeatures.END_POINT)
        .appendPath(trackId)
        .toString()

    fun queryTopTrack(artistId: String, market: String = "US") = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendPath(ApiConstants.TopTracksArtist.ARTISTS)
        .appendPath(artistId)
        .appendPath(ApiConstants.TopTracksArtist.TOP_TRACKS)
        .appendQueryParameter(ApiConstants.TopTracksArtist.MARKET, market)
        .toString()

    fun queryArtist(ids: List<String>) = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendPath(ApiConstants.Artist.END_POINT)
        .appendQueryParameter(ApiConstants.Artist.IDS,ids.joinToString(separator = ","))
        .toString()

    fun queryRelatedArtists(id: String) = buildBaseUri()
        .appendPath(ApiConstants.V1)
        .appendPath(ApiConstants.RelatedArtist.ARTISTS)
        .appendPath(id)
        .appendPath(ApiConstants.RelatedArtist.RELATED_ARTISTS)
        .toString()

}