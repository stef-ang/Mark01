package com.stef_ang.mark01.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.stef_ang.mark01.api.Api
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.model.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    // todo ask: nah status ini hrsnya dihandle repository jg? atau usecase?
    private val _apiStatus = MutableLiveData<Api.Status>()
    @Deprecated("need to adjust repository")
    val apiStatus: LiveData<Api.Status> get() =  _apiStatus

    private val _errorMessage = MutableLiveData<String?>()
    @Deprecated("need to adjust repository")
    val errorMessage: LiveData<String?> get() =  _errorMessage

    private val cacheDB = CacheDB.getDatabase(application)
    private val movieRepository = MovieRepository(cacheDB)

    init {
        // todo ask: ini bisa buat gantiin manual viewModelJob & coroutineScope?
        viewModelScope.launch {
            movieRepository.refreshMovieNowPlaying()
        }
    }

    val nowPlayingMovies = movieRepository.movies

    @Deprecated("use repository")
    fun fetchNowPlayingMovies() {
//        coroutineScope.launch {
//            val deferred = Api.retrofitService.getNowPlayingAsync(1)
//            try {
//                _apiStatus.value = Api.Status.LOADING
//                val listResult = deferred.await()
//                _apiStatus.value = Api.Status.DONE
//                listResult.results?.let { list ->
//                    _movies.value = list.map {
//                        HomeMovie(
//                            it.originalTitle,
//                            it.overview,
//                            Api.BASE_POSTER_URL + it.posterPath,
//                            it.releaseDate,
//                            it.voteAverage
//                        )
//                    }
//                }
//            } catch (e: Exception) {
//                _apiStatus.value = Api.Status.ERROR
//                Log.d("HomeViewModel", e.message)
//                _errorMessage.value = e.message
//            }
//        }
    }

    @Deprecated("need to adjust repository")
    fun onMessageHasShown() {
        _errorMessage.value = null
    }

    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}