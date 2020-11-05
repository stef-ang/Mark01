package com.stef_ang.mark01.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stef_ang.mark01.domain.HomeMovieDomain
import com.stef_ang.mark01.domain.HomeMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeViewModel @Inject constructor(val useCase: HomeMovieUseCase) : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState> get() =  _state

    init {
        _state.value = HomeViewState(null, HomeViewState.State.Loading)
        fetchMovie()
    }

    // we can call this from fragment whenever we need refresh the screen
    private fun fetchMovie() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                useCase.getMovies {
                    updateState(it)
                }
            }
        }
    }

    @VisibleForTesting
    fun updateState(state: HomeMovieDomain) {
        viewModelScope.launch {
            when (state) {
                is HomeMovieDomain.Loading -> _state.value = HomeViewState(null, HomeViewState.State.Loading)
                is HomeMovieDomain.Success -> _state.value = HomeViewState(state.movies, HomeViewState.State.Success)
                is HomeMovieDomain.Error -> _state.value = HomeViewState(
                    _state.value?.movies,
                    HomeViewState.State.Error(state.error)
                )
            }
        }
    }
}