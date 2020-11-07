package com.stef_ang.mark01.util

import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.database.CacheData.Companion.TYPE_HOME_NOW_PLAYING
import com.stef_ang.mark01.database.CacheData.Companion.TYPE_HOME_POPULAR
import com.stef_ang.mark01.database.CacheData.Companion.TYPE_HOME_UPCOMING

enum class HomeMoviesType(
    @CacheData.Companion.DataType val cacheType: String,
    val pathType: String
) {
    NOW_PLAYING(TYPE_HOME_NOW_PLAYING, "now_playing"),
    POPULAR(TYPE_HOME_POPULAR, "popular"),
    UPCOMING(TYPE_HOME_UPCOMING, "upcoming")
}