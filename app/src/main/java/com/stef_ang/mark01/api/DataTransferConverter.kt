package com.stef_ang.mark01.api

import com.stef_ang.mark01.api.datatransfer.MovieDT
import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.util.Helper

fun List<MovieDT>.asCacheData(@CacheData.Companion.DataType type: String): Array<CacheData> {
    return map {
        CacheData(
            it.id,
            type,
            Helper.adapterMovieDT.toJson(it)
        )
    }.toTypedArray()
}