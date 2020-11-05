package com.stef_ang.mark01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stef_ang.mark01.domain.HomeMovie
import com.stef_ang.mark01.domain.HomeMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(val useCase: HomeMovieUseCase) : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState> get() =  _state

//    private val cacheDB = CacheDB.getDatabase(application)
//    private val movieRepository = MovieRepository(cacheDB)

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
//                var movie = getMovies()
//                withContext(Dispatchers.Main) {
////                    _state.value = HomeViewState.Loading(movie)
//                }
//                if (movieRepository.refreshMovieNowPlaying()) {
//                    movie = getMovies()
//                    withContext(Dispatchers.Main) {
//                        _state.value = HomeViewState.Success(movie)
//                    }
//                } else {
//                    movie = getMovies()
//                    withContext(Dispatchers.Main) {
////                        _state.value = HomeViewState.Error(movie, "Ini Error Message")
//                    }
//                }
                useCase.getMovies {
                    updateState(it)
                }
            }
        }
    }

    fun updateState(state: HomeViewState) {
        viewModelScope.launch {
            _state.value = state
        }
    }

//    suspend fun getMovies(): List<HomeMovie>? {
//        return movieRepository.getMovies().asDomainModel()
//    }
//
//    val nowPlayingMovies = movieRepository.movies

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

//    class Factory(val app: Application) : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return HomeViewModel(app) as T
//            }
//            throw IllegalArgumentException("Unable to construct viewmodel")
//        }
//    }
}