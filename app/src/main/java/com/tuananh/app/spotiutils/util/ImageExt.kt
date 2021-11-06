package com.tuananh.app.spotiutils.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tuananh.app.spotiutils.R

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.ic_placeholder_image)
        .placeholder(R.drawable.ic_placeholder_image)
        .into(this)
}