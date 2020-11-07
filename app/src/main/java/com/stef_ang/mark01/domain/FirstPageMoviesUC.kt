package com.stef_ang.mark01.domain

import com.stef_ang.mark01.database.asHomeMovies
import com.stef_ang.mark01.repository.ResponseStatus
import com.stef_ang.mark01.repository.firstpage.FirstPageMovieRepository
import javax.inject.Inject

class FirstPageMoviesUC @Inject constructor(val repository: FirstPageMovieRepository) : IFirstPageMoviesUC {

    override suspend fun getMovies(callback: (HomeMovieDomain) -> Unit) {
        val movies = repository.getMovies().asHomeMovies()
        if (movies.isNotEmpty()) {
            callback(HomeMovieDomain.Success(movies))
        } else {
            callback(HomeMovieDomain.Loading)
        }

        val result = repository.refreshMovieSection()
        if (result is ResponseStatus.Success) {
            callback(HomeMovieDomain.Success(repository.getMovies().asHomeMovies()))
        } else {
            callback(HomeMovieDomain.Error((result as ResponseStatus.Error).error))
        }
    }
}