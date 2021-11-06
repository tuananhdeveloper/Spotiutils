package com.tuananh.app.spotiutils.data.remote.api

object ApiConstants {

    const val SCHEME_HTTPS = "https"
    const val AUTHORITY_SPOTIFY_API = "api.spotify.com"
    const val AUTHORITY_ACCOUNT = "accounts.spotify.com"
    const val V1 = "v1"

    object RelatedArtist {
        const val RELATED_ARTISTS = "related-artists"
        const val ARTISTS = "artists"
    }

    object AccessToken {
        const val END_POINT = "api/token"
        const val GRANT_TYPE = "grant_type"
        const val CODE = "code"
        const val REDIRECT_URI = "redirect_uri"
    }

    object RefreshedToken {
        const val END_POINT = "api/token"
        const val GRANT_TYPE = "grant_type"
        const val REFRESH_TOKEN = "refresh_token"
    }

    object LastPlayedTrack {
        const val END_POINT = "me/player/recently-played"
        const val LIMIT = "limit"
    }

    object Playlists {
        const val END_POINT = "me/playlists"
        const val LIMIT = "limit"

    }

    object TopTrack {
        const val END_POINT = "me/top/tracks"
        const val LIMIT = "limit"
        const val TIME_RANGE = "time_range"
    }

    object TopArtist {
        const val END_POINT = "me/top/artists"
        const val LIMIT = "limit"
        const val TIME_RANGE = "time_range"
    }

    object Track {
        const val END_POINT = "tracks"
    }

    object Status {
        const val CODE_200 = 200 //OK
        const val CODE_401 = 401 //Unauthorized
    }

    object CurrentUserProfile {
        const val END_POINT = "me"
    }

    object AudioFeatures {
        const val END_POINT = "audio-features"
    }

    object TopTracksArtist {
        const val ARTISTS = "artists"
        const val TOP_TRACKS = "top-tracks"
        const val MARKET = "market"
    }

    object Artist {
        const val IDS = "ids"
        const val END_POINT = "artists"
    }
}