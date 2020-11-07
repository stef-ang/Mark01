package com.stef_ang.mark01.domain

import com.stef_ang.mark01.repository.IFirstPageMovieRepository
import com.stef_ang.mark01.repository.ResponseStatus
import com.stef_ang.mark01.util.HomeMoviesType
import javax.inject.Inject

class FirstPageMoviesUC @Inject constructor(val repository: IFirstPageMovieRepository) : IFirstPageMoviesUC {

    override suspend fun getMovies(type: HomeMoviesType, callback: (HomeMovieDomain) -> Unit) {
        val movies = repository.getMovies(type)
        if (movies.isNotEmpty()) {
            callback(HomeMovieDomain.Success(movies))
        } else {
            callback(HomeMovieDomain.Loading)
        }

        val result = repository.refreshMovieSection(type)
        if (result is ResponseStatus.Success) {
            callback(HomeMovieDomain.Success(repository.getMovies(type)))
        } else {
            callback(HomeMovieDomain.Error((result as ResponseStatus.Error).error))
        }
    }
}