package com.stef_ang.mark01.repository

import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.repository.ResponseStatus
import com.stef_ang.mark01.util.HomeMoviesType

interface IFirstPageMovieRepository {

    fun getMovies(type: HomeMoviesType): List<CacheData>

    suspend fun refreshMovieSection(type: HomeMoviesType): ResponseStatus
}