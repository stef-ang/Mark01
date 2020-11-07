package com.stef_ang.mark01.util

import androidx.lifecycle.LiveData
import com.stef_ang.mark01.database.asHomeMovies
import com.stef_ang.mark01.domain.HomeMovieDomain
import com.stef_ang.mark01.viewmodel.home.HomeViewState

fun HomeMovieDomain.asHomeViewState(_state: LiveData<HomeViewState>): HomeViewState {
    return when (this) {
        is HomeMovieDomain.Loading -> HomeViewState(null, HomeViewState.State.Loading)
        is HomeMovieDomain.Success -> HomeViewState(this.movies.asHomeMovies(), HomeViewState.State.Success)
        is HomeMovieDomain.Error -> HomeViewState(_state.value?.movies, HomeViewState.State.Error(this.error))
    }
}