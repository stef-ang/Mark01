package com.stef_ang.mark01.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stef_ang.mark01.domain.IFirstPageMoviesUC
import com.stef_ang.mark01.util.CoroutineContextProvider
import com.stef_ang.mark01.util.HomeMoviesType
import com.stef_ang.mark01.util.asHomeViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeNowPlayingVM @Inject constructor(
    private val useCase: IFirstPageMoviesUC,
    private val contextProvider: CoroutineContextProvider
) : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState> get() =  _state

    init {
        _state.value = HomeViewState(
            null,
            HomeViewState.State.Loading
        )
        fetchMovie()
    }

    // we can call this from fragment whenever we need refresh the screen
    private fun fetchMovie() {
        viewModelScope.launch(contextProvider.io) {
            useCase.getMovies(HomeMoviesType.NOW_PLAYING) {
                _state.postValue(it.asHomeViewState(_state))
            }
        }
    }
}