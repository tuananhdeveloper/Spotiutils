package com.tuananh.app.spotiutils.util
import com.tuananh.app.spotiutils.R
import com.tuananh.app.spotiutils.data.remote.model.TokenData
import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun String.toDate(format: String): Date {
    val sdf = SimpleDateFormat(format, Locale.US)
    return sdf.parse(this) ?: throw ParseException(R.string.error_parse_exception.toString(), 0)
}

fun String.toTimeStamp(): Timestamp {
    val pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    val sdf = SimpleDateFormat(pattern, Locale.US)
    val date = sdf.parse(this) ?: throw ParseException(R.string.error_parse_exception.toString(), 0)
    return Timestamp(date.time)
}

fun TokenData.expire(): Boolean = System.currentTimeMillis() > this.expireAt.time

fun Timestamp.getDifference(): String {
    val diff = System.currentTimeMillis() - this.time
    val numDays =  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)

    var result = ""

    if(numDays < 7) {
        result = "${numDays}d"
    }
    else if(numDays in 7..29) {
        result = "${numDays/7}w"
    }
    else if(numDays in 30..365) {
        result = "${numDays/30}m"
    }
    else {
        result = "${numDays/365}y"
    }
    return result
}

fun miliSecToTrackLength(ms: Int): String {
    var seconds = "${(ms/1000) % 60}"
    var minutes = "${(ms/(1000*60)) % 60}"
    if(seconds.length < 2) seconds="0$seconds"
    if(minutes.length < 2) minutes="0$minutes"
    return "$minutes:$seconds"
}
