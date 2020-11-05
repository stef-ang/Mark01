package com.stef_ang.mark01.di

import android.app.Application
import com.stef_ang.mark01.api.Api
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun provideRepository(application: Application): MovieRepository {
        return MovieRepository(
            CacheDB.getDatabase(application),
            Api.retrofitService
        )
    }
}