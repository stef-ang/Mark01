package com.stef_ang.mark01.api

import com.stef_ang.mark01.BuildConfig
import com.stef_ang.mark01.api.data.Movie
import com.stef_ang.mark01.api.data.PaginationResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    // todo biar gk manual nambah di masing2 endpoint gmn ya?
    @GET("movie/now_playing?api_key=${BuildConfig.TMDB_API3_KEY}")
    fun getNowPlayingAsync(@Query("page") page: Int): Deferred<PaginationResult<Movie>>
}