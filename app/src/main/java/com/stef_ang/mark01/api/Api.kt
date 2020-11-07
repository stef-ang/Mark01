package com.stef_ang.mark01.api

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.stef_ang.mark01.util.Helper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Api {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w342"

    val networkFlipperPlugin = NetworkFlipperPlugin()

    private val okHttpClient get() = OkHttpClient.Builder()
        .addInterceptor(NetworkInterceptor())
        .addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin, true))
        .pingInterval(30, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(Helper.moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService : MovieService by lazy{
        retrofit.create(MovieService::class.java)
    }
}