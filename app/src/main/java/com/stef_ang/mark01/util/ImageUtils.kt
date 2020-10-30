package com.stef_ang.mark01.util

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide

object ImageUtils {

    fun loadImage(url: String, image: ImageView) {
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(image.context)
            .load(imgUri)
            .into(image)
    }
}