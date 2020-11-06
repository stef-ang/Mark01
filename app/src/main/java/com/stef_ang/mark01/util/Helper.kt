package com.stef_ang.mark01.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.stef_ang.mark01.api.datatransfer.MovieDT

object Helper {

    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val adapterMovieDT: JsonAdapter<MovieDT> = moshi.adapter(MovieDT::class.java)
}