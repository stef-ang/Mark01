package com.stef_ang.mark01.repository.firstpage

import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.repository.ResponseStatus

interface FirstPageMovieRepository {

    @CacheData.Companion.DataType
    val types: String

    fun getMovies(): List<CacheData>

    suspend fun refreshMovieSection(): ResponseStatus
}