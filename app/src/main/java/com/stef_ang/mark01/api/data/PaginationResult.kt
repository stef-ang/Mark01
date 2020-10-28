package com.stef_ang.mark01.api.data

import com.squareup.moshi.Json

data class PaginationResult<T>(
    val page: Int,
    val results: List<T>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)