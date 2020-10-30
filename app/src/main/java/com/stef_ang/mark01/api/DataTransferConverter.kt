package com.stef_ang.mark01.api

import com.stef_ang.mark01.api.datatransfer.MovieDT
import com.stef_ang.mark01.database.entity.MovieDO

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