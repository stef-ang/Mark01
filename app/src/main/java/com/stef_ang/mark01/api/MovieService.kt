package com.stef_ang.mark01.api

import com.stef_ang.mark01.BuildConfig
import com.stef_ang.mark01.api.datatransfer.MovieDT
import com.stef_ang.mark01.api.datatransfer.MovieDetailDT
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    // todo biar gk manual nambah di masing2 endpoint gmn ya?
    @GET("movie/now_playing?api_key=${BuildConfig.TMDB_API3_KEY}")
    suspend fun getNowPlayingAsync(@Query("page") page: Int): Response<PaginationResult<MovieDT>>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: Int): Deferred<MovieDetailDT>
}