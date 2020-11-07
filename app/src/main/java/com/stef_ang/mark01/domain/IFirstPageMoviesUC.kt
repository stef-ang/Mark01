package com.stef_ang.mark01.domain

interface IFirstPageMoviesUC {

    suspend fun getMovies(callback: (HomeMovieDomain) -> Unit)
}