package com.stef_ang.mark01.domain

import com.stef_ang.mark01.util.HomeMoviesType

interface IFirstPageMoviesUC {

    suspend fun getMovies(type: HomeMoviesType, callback: (HomeMovieDomain) -> Unit)
}