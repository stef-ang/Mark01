package com.stef_ang.mark01.viewmodel.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.slmyldz.random.Randoms
import com.stef_ang.mark01.TestContextProvider
import com.stef_ang.mark01.database.asHomeMovies
import com.stef_ang.mark01.domain.HomeMovieDomain
import com.stef_ang.mark01.domain.IFirstPageMoviesUC
import com.stef_ang.mark01.getMovieCacheData
import com.stef_ang.mark01.util.HomeMoviesType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeNowPlayingVMTest {

    // handle liveData.postValue
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // handle swapping coroutine provider or dispatcher
    private val testContextProvider = TestContextProvider()

    private val listCacheData by lazy {
        arrayListOf(getMovieCacheData(), getMovieCacheData(), getMovieCacheData(), getMovieCacheData())
    }

    @Test
    fun `on state loading`() {
        val useCase = object : IFirstPageMoviesUC {
            override suspend fun getMovies(type: HomeMoviesType, callback: (HomeMovieDomain) -> Unit) {
                callback(HomeMovieDomain.Loading)
            }
        }

        // pause / hold viewModelScope.launch so that we can assert initial value
        testContextProvider.testCoroutineDispatcher.pauseDispatcher()

        val viewModel = HomeNowPlayingVM(useCase, testContextProvider)

        // assert initial value
        assertNotNull(viewModel.state.value)
        assertNull(viewModel.state.value?.movies)
        assertEquals(viewModel.state.value?.state, HomeViewState.State.Loading)

        // continue process viewModelScope.launch
        testContextProvider.testCoroutineDispatcher.resumeDispatcher()

        assertNotNull(viewModel.state.value)
        assertNull(viewModel.state.value?.movies)
        assertEquals(viewModel.state.value?.state, HomeViewState.State.Loading)
    }

    @Test
    fun `on state success`() {
        val useCase = object : IFirstPageMoviesUC {
            override suspend fun getMovies(type: HomeMoviesType, callback: (HomeMovieDomain) -> Unit) {
                callback(HomeMovieDomain.Success(listCacheData))
            }
        }

        // pause / hold viewModelScope.launch so that we can assert initial value
        testContextProvider.testCoroutineDispatcher.pauseDispatcher()

        val viewModel = HomeNowPlayingVM(useCase, testContextProvider)

        // assert initial value
        assertNotNull(viewModel.state.value)
        assertNull(viewModel.state.value?.movies)
        assertEquals(viewModel.state.value?.state, HomeViewState.State.Loading)

        // continue process viewModelScope.launch
        testContextProvider.testCoroutineDispatcher.resumeDispatcher()

        assertNotNull(viewModel.state.value?.movies)
        assertEquals(viewModel.state.value?.movies, listCacheData.asHomeMovies())
        assertEquals(viewModel.state.value?.state, HomeViewState.State.Success)
    }

    @Test
    fun `on state pure error`() {
        val errorMessage = Randoms.alphaNumericString(20)
        val useCase = object : IFirstPageMoviesUC {
            override suspend fun getMovies(type: HomeMoviesType, callback: (HomeMovieDomain) -> Unit) {
                callback(HomeMovieDomain.Error(errorMessage))
            }
        }

        // pause / hold viewModelScope.launch so that we can assert initial value
        testContextProvider.testCoroutineDispatcher.pauseDispatcher()

        val viewModel = HomeNowPlayingVM(useCase, testContextProvider)

        // assert initial value
        assertNotNull(viewModel.state.value)
        assertNull(viewModel.state.value?.movies)
        assertEquals(viewModel.state.value?.state, HomeViewState.State.Loading)

        // continue process viewModelScope.launch
        testContextProvider.testCoroutineDispatcher.resumeDispatcher()

        assertNull(viewModel.state.value?.movies)
        assertEquals(viewModel.state.value?.state, HomeViewState.State.Error(errorMessage))
    }

    @Test
    fun `on state error but cache is exist`() {
        val errorMessage = Randoms.alphaNumericString(20)
        val useCase = object : IFirstPageMoviesUC {
            override suspend fun getMovies(type: HomeMoviesType, callback: (HomeMovieDomain) -> Unit) {
                callback(HomeMovieDomain.Success(listCacheData))
                callback(HomeMovieDomain.Error(errorMessage))
            }
        }

        // pause / hold viewModelScope.launch so that we can assert initial value
        testContextProvider.testCoroutineDispatcher.pauseDispatcher()

        val viewModel = HomeNowPlayingVM(useCase, testContextProvider)

        // assert initial value
        assertNotNull(viewModel.state.value)
        assertNull(viewModel.state.value?.movies)
        assertEquals(viewModel.state.value?.state, HomeViewState.State.Loading)

        // continue process viewModelScope.launch
        testContextProvider.testCoroutineDispatcher.resumeDispatcher()

        assertNotNull(viewModel.state.value?.movies)
        assertEquals(viewModel.state.value?.movies, listCacheData.asHomeMovies())
        assertEquals(viewModel.state.value?.state, HomeViewState.State.Error(errorMessage))
    }
}