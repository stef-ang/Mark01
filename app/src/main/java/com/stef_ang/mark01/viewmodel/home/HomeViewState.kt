package com.stef_ang.mark01.viewmodel.home

import com.stef_ang.mark01.domain.HomeMovie

// HomeFragment - HomeViewModel (Actions) - HomeViewState
data class HomeViewState(
    val movies: List<HomeMovie>?,
    val state: State
) {

    sealed class State {
        object Loading: State()
        object Success: State()
        data class Error(val error: String): State()
    }
}