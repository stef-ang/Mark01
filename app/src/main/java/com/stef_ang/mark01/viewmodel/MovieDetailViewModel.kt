package com.stef_ang.mark01.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.stef_ang.mark01.database.CacheDB
//import com.stef_ang.mark01.model.MovieDetailRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    movieId: Int,
    application: Application
) : AndroidViewModel(application) {

    private val cacheDB = CacheDB.getDatabase(application)
//    private val repository = MovieDetailRepository(movieId, cacheDB)

    init {
        viewModelScope.launch {
//            repository.refreshMovieDetail()
        }
    }

//    val movieDetail = repository.movieDetail

    class Factory(val movieId: Int, val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieDetailViewModel(movieId, app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}