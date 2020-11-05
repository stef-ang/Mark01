package com.stef_ang.mark01.domain

data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String?,
    val genres: String
)