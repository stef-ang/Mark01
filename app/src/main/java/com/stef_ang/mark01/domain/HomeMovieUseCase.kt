package com.stef_ang.mark01.domain

import com.stef_ang.mark01.database.asDomainModel
import com.stef_ang.mark01.repository.MovieRepository
import com.stef_ang.mark01.repository.ResponseStatus
import javax.inject.Inject

class HomeMovieUseCase @Inject constructor(val movieRepository: MovieRepository) {

    suspend fun getMovies(callback: (HomeMovieDomain) -> Unit) {
        val movies = movieRepository.getMovies().asDomainModel()
        if (movies.isNotEmpty()) {
            callback(HomeMovieDomain.Success(movies))
        } else {
            callback(HomeMovieDomain.Loading)
        }

        val result = movieRepository.refreshMovieNowPlaying()
        if (result is ResponseStatus.Success) {
            callback(HomeMovieDomain.Success(movieRepository.getMovies().asDomainModel()))
        } else {
            callback(HomeMovieDomain.Error((result as ResponseStatus.Error).error))
        }
    }
}