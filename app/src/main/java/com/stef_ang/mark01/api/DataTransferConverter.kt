package com.stef_ang.mark01.api

import com.stef_ang.mark01.api.datatransfer.MovieDT
import com.stef_ang.mark01.api.datatransfer.MovieDetailDT
import com.stef_ang.mark01.database.entity.MovieDO
import com.stef_ang.mark01.database.entity.MovieDetailDO
import java.lang.StringBuilder

fun List<MovieDT>.asDatabaseModel(): Array<MovieDO> {
    return map {
        MovieDO(
            it.id,
            it.adult,
            it.backdropPath,
            it.originalLanguage,
            it.originalTitle,
            it.overview,
            it.popularity,
            it.posterPath,
            it.releaseDate,
            it.title,
            it.video,
            it.voteAverage,
            it.voteCount
        )
    }.toTypedArray()
}

fun MovieDetailDT.asDatabaseModel(): MovieDetailDO {
    val genreBuilder = StringBuilder(10_000)
    for (genre in genres) {
        genreBuilder.append(genre.name)
    }
    return MovieDetailDO(
        id,
        adult,
        backdropPath,
        budget,
        genreBuilder.toString(),
        homepage,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        revenue,
        runtime,
        status,
        tagline,
        title,
        video,
        voteAverage,
        voteCount
    )
}