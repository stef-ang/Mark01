package com.stef_ang.mark01.database

import com.stef_ang.mark01.api.Api
import com.stef_ang.mark01.api.datatransfer.MovieDT
import com.stef_ang.mark01.domain.HomeMovie
import com.stef_ang.mark01.util.Helper

fun CacheData.asHomeMovie(): HomeMovie {
    val movieDT: MovieDT = Helper.adapterMovieDT.fromJson(this.serializedObject) ?: MovieDT(id = 0)
    return HomeMovie(
        id,
        movieDT.title,
        movieDT.overview,
        Api.BASE_POSTER_URL + movieDT.posterPath,
        movieDT.releaseDate,
        movieDT.voteAverage
    )
}

fun List<CacheData>.asHomeMovies(): List<HomeMovie> {
    return map { it.asHomeMovie() }
}