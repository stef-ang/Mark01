package com.stef_ang.mark01.di.home

import com.stef_ang.mark01.api.MovieService
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.di.FragmentScope
import com.stef_ang.mark01.domain.FirstPageMoviesUC
import com.stef_ang.mark01.domain.IFirstPageMoviesUC
import com.stef_ang.mark01.repository.FirstPageMovieRepository
import com.stef_ang.mark01.repository.IFirstPageMovieRepository
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @FragmentScope
    @Provides
    fun provideFirstPageNowPlayingRepository(
        cacheDB: CacheDB,
        remoteService: MovieService
    ): IFirstPageMovieRepository {
        return FirstPageMovieRepository(cacheDB, remoteService)
    }

    @FragmentScope
    @Provides
    fun provideNowPlayingMoviesUC(repository: IFirstPageMovieRepository): IFirstPageMoviesUC {
        return FirstPageMoviesUC(repository)
    }
}