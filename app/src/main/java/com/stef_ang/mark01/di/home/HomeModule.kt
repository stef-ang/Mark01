package com.stef_ang.mark01.di.home

import com.stef_ang.mark01.api.MovieService
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.di.HomeNowPlaying
import com.stef_ang.mark01.di.HomePopular
import com.stef_ang.mark01.di.HomeUpcoming
import com.stef_ang.mark01.domain.FirstPageMoviesUC
import com.stef_ang.mark01.domain.IFirstPageMoviesUC
import com.stef_ang.mark01.repository.firstpage.FirstPageMovieRepository
import com.stef_ang.mark01.repository.firstpage.FirstPageNowPlayingRepository
import com.stef_ang.mark01.repository.firstpage.FirstPagePopularRepository
import com.stef_ang.mark01.repository.firstpage.FirstPageUpcomingRepository
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @HomeNowPlaying
    @Provides
    fun provideFirstPageNowPlayingRepository(
        cacheDB: CacheDB,
        remoteService: MovieService
    ): FirstPageMovieRepository {
        return FirstPageNowPlayingRepository(cacheDB, remoteService)
    }

    @HomePopular
    @Provides
    fun provideFirstPagePopularRepository(
        cacheDB: CacheDB,
        remoteService: MovieService
    ): FirstPageMovieRepository {
        return FirstPagePopularRepository(cacheDB, remoteService)
    }

    @HomeUpcoming
    @Provides
    fun provideFirstPageUpcomingRepository(
        cacheDB: CacheDB,
        remoteService: MovieService
    ): FirstPageMovieRepository {
        return FirstPageUpcomingRepository(cacheDB, remoteService)
    }

    // not sure about this repetition
    @HomeNowPlaying
    @Provides
    fun provideNowPlayingMoviesUC(@HomeNowPlaying repository: FirstPageMovieRepository): IFirstPageMoviesUC {
        return FirstPageMoviesUC(repository)
    }

    @HomePopular
    @Provides
    fun providePopularMoviesUC(@HomePopular repository: FirstPageMovieRepository): IFirstPageMoviesUC {
        return FirstPageMoviesUC(repository)
    }


    @HomeUpcoming
    @Provides
    fun provideUpcomingMoviesUC(@HomeUpcoming repository: FirstPageMovieRepository): IFirstPageMoviesUC {
        return FirstPageMoviesUC(repository)
    }
}