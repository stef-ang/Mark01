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

    private val _rawData = MutableLiveData<String>()
    val rawData: LiveData<String> get() =  _rawData

    private val _movies = MutableLiveData<List<HomeMovieData>>()
    val movies: LiveData<List<HomeMovieData>> get() =  _movies

    fun fetchNowPlayingMovies() {
        coroutineScope.launch {
            val deferred = Api.retrofitService.getNowPlayingAsync(1)
            try {
                val listResult = deferred.await()
                listResult.results?.let { list ->
                    _rawData.value = "size: ${list.size}"
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
                Log.d("HomeViewModel", e.message)
                _rawData.value = "Failure ${e.message}"
            }
        }
    }

    override fun onCleared(){
        super.onCleared()
        viewModelJob.cancel()
    }
}