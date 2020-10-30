package com.stef_ang.mark01.database

import com.stef_ang.mark01.api.Api
import com.stef_ang.mark01.database.entity.MovieDO
import com.stef_ang.mark01.domain.HomeMovie

fun MovieDO.asDomainModel(): HomeMovie {
    return HomeMovie(
        title,
        overview,
        Api.BASE_POSTER_URL + posterPath,
        releaseDate,
        voteAverage
    )
}

fun List<MovieDO>.asDomainModel(): List<HomeMovie> {
    return map { it.asDomainModel() }
}