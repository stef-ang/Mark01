package com.stef_ang.mark01.domain

sealed class HomeMovieDomain {
    object Loading: HomeMovieDomain()
    data class Success(val movies: List<HomeMovie>): HomeMovieDomain()
    data class Error(val error: String): HomeMovieDomain()
}