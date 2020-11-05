package com.stef_ang.mark01.domain

import com.stef_ang.mark01.database.asDomainModel
import com.stef_ang.mark01.model.MovieRepository
import com.stef_ang.mark01.viewmodel.HomeViewState
import javax.inject.Inject

class HomeMovieUseCase @Inject constructor(val movieRepository: MovieRepository) {

    suspend fun getMovies(callback: (HomeViewState) -> Unit) {
        val movies = movieRepository.getMovies().asDomainModel()
        if (movies.isNotEmpty()) {
            callback(HomeViewState.Success(movies))
        } else {
            callback(HomeViewState.Loading)
        }

        val result = movieRepository.refreshMovieNowPlaying()
        if (result.first) {
            callback(HomeViewState.Success(movieRepository.getMovies().asDomainModel()))
        } else {
            callback(HomeViewState.Error(result.second))
        }
    }
}