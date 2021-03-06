package com.stef_ang.mark01.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stef_ang.mark01.domain.IFirstPageMoviesUC
import com.stef_ang.mark01.util.HomeMoviesType
import com.stef_ang.mark01.util.asHomeViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeUpcomingVM @Inject constructor(val useCase: IFirstPageMoviesUC) : ViewModel() {

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
        // todo for testable, use CoroutineContextProvider as HomeNowPlayingVM
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getMovies(HomeMoviesType.UPCOMING) {
                _state.postValue(it.asHomeViewState(_state))
            }
        }
    }
}