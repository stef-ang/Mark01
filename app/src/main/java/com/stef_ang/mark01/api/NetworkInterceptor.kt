package com.stef_ang.mark01.api

import com.stef_ang.mark01.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API3_KEY)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}