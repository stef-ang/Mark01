package com.stef_ang.mark01.viewmodel

import com.stef_ang.mark01.domain.HomeMovie

// HomeFragment - HomeViewModel (Actions) - HomeViewState
sealed class HomeViewState() {
//    data class Loading(val movies: List<HomeMovie>?): HomeViewState()
    object Loading: HomeViewState()
    data class Success(val movies: List<HomeMovie>?): HomeViewState()
    data class Error(val error: String): HomeViewState()
//    data class Error(val movies: List<HomeMovie>?, val error: String): HomeViewState()
}