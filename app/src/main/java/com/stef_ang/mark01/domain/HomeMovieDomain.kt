package com.stef_ang.mark01.domain

import com.stef_ang.mark01.database.CacheData

sealed class HomeMovieDomain {
    object Loading: HomeMovieDomain()
    data class Success(val movies: List<CacheData>): HomeMovieDomain()
    data class Error(val error: String): HomeMovieDomain()
}