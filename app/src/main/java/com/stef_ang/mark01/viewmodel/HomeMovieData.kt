package com.stef_ang.mark01.viewmodel

import java.io.Serializable

data class HomeMovieData(
    val title: String?,
    val overview: String?,
    val image: String?,
    val releaseDate: String?,
    val rating: Double?
): Serializable