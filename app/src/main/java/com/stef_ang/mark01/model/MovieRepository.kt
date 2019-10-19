package com.stef_ang.mark01.model

interface MovieRepository {

    fun getPopularMovies(page: Int, limit: Int)
}