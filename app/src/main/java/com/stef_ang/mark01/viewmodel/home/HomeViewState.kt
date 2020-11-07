package com.stef_ang.mark01.viewmodel.home

import com.stef_ang.mark01.domain.HomeMovie

// HomeFragment - HomeViewModel (Actions) - HomeViewState
data class HomeViewState(
    var movies: List<HomeMovie>?,
    var state: State
) {

    sealed class State {
        object Loading: State()
        object Success: State()
        data class Error(val error: String): State()
    }
}