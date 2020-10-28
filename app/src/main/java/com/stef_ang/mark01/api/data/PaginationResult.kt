package com.stef_ang.mark01.api.data

import com.squareup.moshi.Json

data class PaginationResult<T>(

    @Json(name="dates")
    val dates: Dates? = null,

    @Json(name="page")
    val page: Int? = null,

    @Json(name="total_pages")
    val totalPages: Int? = null,

    @Json(name="results")
    val results: List<T>? = null,

    @Json(name="total_results")
    val totalResults: Int? = null
) {

    data class Dates(

        @Json(name="maximum")
        val maximum: String? = null,

        @Json(name="minimum")
        val minimum: String? = null
    )
}