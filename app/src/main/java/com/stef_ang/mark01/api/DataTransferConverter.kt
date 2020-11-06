package com.stef_ang.mark01.api

import com.stef_ang.mark01.api.datatransfer.MovieDT
import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.util.Helper

fun List<MovieDT>.asCacheData(): Array<CacheData> {
    return map {
        CacheData(
            it.id,
            CacheData.TYPE_MOVIE,
            Helper.adapterMovieDT.toJson(it)
        )
    }.toTypedArray()
}