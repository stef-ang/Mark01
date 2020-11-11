package com.stef_ang.mark01

import com.slmyldz.random.Randoms
import com.stef_ang.mark01.api.datatransfer.MovieDT
import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.database.CacheData.Companion.TYPE_HOME_NOW_PLAYING
import com.stef_ang.mark01.database.CacheData.Companion.TYPE_HOME_POPULAR
import com.stef_ang.mark01.database.CacheData.Companion.TYPE_HOME_UPCOMING
import com.stef_ang.mark01.util.Helper

val cacheDataTypes = arrayListOf(
    TYPE_HOME_NOW_PLAYING,
    TYPE_HOME_POPULAR,
    TYPE_HOME_UPCOMING
)

fun getMovieCacheData(type: String? = null): CacheData {
    val dataType = type ?: cacheDataTypes[Randoms.Integer(0, cacheDataTypes.size - 1)]
    val id = Randoms.Integer(0, 1_000_000)
    val movieDT = MovieDT(
        title = Randoms.alphaNumericString(10),
        overview = Randoms.alphaNumericString(50),
        id = id
    )
    return CacheData(id, "${id}_$dataType", dataType, Randoms.Integer(0, 100), Helper.adapterMovieDT.toJson(movieDT))
}