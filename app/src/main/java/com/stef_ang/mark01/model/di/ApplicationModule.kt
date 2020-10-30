package com.stef_ang.mark01.model.di

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.stef_ang.mark01.api.Api
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

//@Module
//internal abstract class ApplicationModule {
//
//    @Singleton
//    @Binds
//    internal abstract fun bindContext(application: Application): Context
//
//    companion object {
//
//        @Singleton
//        @Provides
//        fun provideGlide(context: Context): Glide {
//            return Glide.get(context)
//        }
//
//        @Singleton
//        @Provides
//        fun provideRetrofit(): Retrofit {
//            return Retrofit.Builder()
//                .addConverterFactory(MoshiConverterFactory.create())
//                .baseUrl(Api.BASE_URL)
//                .build()
//        }
//    }
//}