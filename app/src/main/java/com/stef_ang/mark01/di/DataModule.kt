package com.stef_ang.mark01.di

import android.app.Application
import com.stef_ang.mark01.api.Api
import com.stef_ang.mark01.api.MovieService
import com.stef_ang.mark01.database.CacheDB
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideServices(): MovieService {
        return Api.retrofitService
    }

    @Provides
    fun provideCache(application: Application): CacheDB {
        return CacheDB.getDatabase(application)
    }
}