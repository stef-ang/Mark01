package com.stef_ang.mark01.api.datatransfer

import com.squareup.moshi.Json

data class MovieDetailDT(
    @Json(name = "adult") val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "budget") val budget: Int,
    @Json(name = "genres") val genres: List<GenresItem>,
    @Json(name = "homepage") val homepage: String?,
    @Json(name = "id") val id: Int,
    @Json(name = "imdb_id") val imdbId: String?,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "production_companies") val productionCompanies: List<ProductionCompaniesItem>,
    @Json(name = "production_countries") val productionCountries: List<ProductionCountriesItem>,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "revenue") val revenue: Int,
    @Json(name = "runtime") val runtime: Int,
    @Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguagesItem>,
    @Json(name = "status") val status: String,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "title") val title: String,
    @Json(name = "video") val video: Boolean,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
) {

    data class ProductionCountriesItem(

        @Json(name = "iso_3166_1")
        val iso31661: String,

        @Json(name = "name")
        val name: String
    )

    data class ProductionCompaniesItem(

        @Json(name = "logo_path")
        val logoPath: Any,

        @Json(name = "name")
        val name: String,

        @Json(name = "id")
        val id: Int,

        @Json(name = "origin_country")
        val originCountry: String
    )

    data class GenresItem(

        @Json(name = "name")
        val name: String,

        @Json(name = "id")
        val id: Int
    )

    data class SpokenLanguagesItem(

        @Json(name = "name")
        val name: String,

        @Json(name = "iso_639_1")
        val iso6391: String
    )
}
