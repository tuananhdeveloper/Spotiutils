package com.tuananh.app.spotiutils.util

val keys = listOf<String>("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")

fun Int.toKey(): String {
    return keys[this]
}