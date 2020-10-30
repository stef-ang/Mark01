package com.stef_ang.mark01.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stef_ang.mark01.api.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _apiStatus = MutableLiveData<Api.Status>()
    val apiStatus: LiveData<Api.Status> get() =  _apiStatus

    private val _movies = MutableLiveData<List<HomeMovieData>>()
    val movies: LiveData<List<HomeMovieData>> get() =  _movies

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() =  _errorMessage

    fun fetchNowPlayingMovies() {
        coroutineScope.launch {
            val deferred = Api.retrofitService.getNowPlayingAsync(1)
            try {
                _apiStatus.value = Api.Status.LOADING
                val listResult = deferred.await()
                _apiStatus.value = Api.Status.DONE
                listResult.results?.let { list ->
                    _movies.value = list.map {
                        HomeMovieData(
                            it.originalTitle,
                            it.overview,
                            Api.BASE_POSTER_URL + it.posterPath,
                            it.releaseDate,
                            it.voteAverage
                        )
                    }
                }
            } catch (e: Exception) {
                _apiStatus.value = Api.Status.ERROR
                Log.d("HomeViewModel", e.message)
                _errorMessage.value = e.message
            }
        }
    }

    fun onMessageHasShown() {
        _errorMessage.value = null
    }

    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }
}