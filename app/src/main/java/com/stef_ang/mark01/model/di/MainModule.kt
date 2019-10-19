package com.stef_ang.mark01.model.di

import android.graphics.Movie
import androidx.recyclerview.widget.RecyclerView
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import retrofit2.Retrofit

@Module
abstract class MainModule {

//    @FragmentScoped
//    @ContributesAndroidInjector
//    internal abstract fun provideMainFragment(): MainFragment
//
//    @ActivityScoped
//    @Binds
//    internal abstract fun provideMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter
//
//    @ActivityScoped
//    @Binds
//    internal abstract fun provideMainAdapter(mainAdapter: MainAdapter): RecyclerView.Adapter<MainAdapter.MainVH>
//
//    @ActivityScoped
//    @Binds
//    internal abstract fun provideModieDataSource(movieRemoteDataSource: MovieRemoteDataSource): DataSource<Movie>
//
//    companion object {
//
//        @ActivityScoped
//        @Provides
//        internal fun provideMovieApi(retrofit: Retrofit): MovieRemoteDataSource.MovieApi {
//            return retrofit.create<MovieRemoteDataSource.MovieApi>(MovieRemoteDataSource.MovieApi::class.java!!)
//        }
//    }
}