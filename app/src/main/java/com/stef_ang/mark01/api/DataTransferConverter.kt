package com.stef_ang.mark01.api

import com.stef_ang.mark01.api.datatransfer.MovieDT
import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.util.Helper

fun List<MovieDT>.asCacheData(@CacheData.Companion.DataType type: String): Array<CacheData> {
    return mapIndexed { index, movie ->
        CacheData(
            movie.id,
            "${type}_${movie.id}",
            type,
            index,
            Helper.adapterMovieDT.toJson(movie)
        )
    }.toTypedArray()
}