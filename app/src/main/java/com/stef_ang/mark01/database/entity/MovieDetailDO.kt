package com.stef_ang.mark01.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stef_ang.mark01.api.datatransfer.MovieDetailDT

@Entity(tableName = "movie_detail")
class MovieDetailDO(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val budget: Int,
    val genres: String,
    val homepage: String?,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)