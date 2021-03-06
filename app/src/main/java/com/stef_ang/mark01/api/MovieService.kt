package com.stef_ang.mark01.api

import com.stef_ang.mark01.api.datatransfer.MovieDT
import com.stef_ang.mark01.api.datatransfer.MovieDetailDT
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{type}")
    suspend fun getMoviesAsync(
        @Path("type") type: String,
        @Query("page") page: Int
    ): Response<PaginationResult<MovieDT>>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: Int): Deferred<MovieDetailDT>
}