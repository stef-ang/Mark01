package com.stef_ang.mark01.domain

import com.slmyldz.random.Randoms
import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.getMovieCacheData
import com.stef_ang.mark01.repository.IFirstPageMovieRepository
import com.stef_ang.mark01.repository.ResponseStatus
import com.stef_ang.mark01.util.HomeMoviesType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FirstPageMoviesUCTest {

    private lateinit var useCase: FirstPageMoviesUC

    private val listCacheData by lazy {
        arrayListOf(getMovieCacheData(), getMovieCacheData(), getMovieCacheData(), getMovieCacheData())
    }

    @Test
    fun `get movie on success from cache and success`() {
        var onSuccessCounter = 0
        var cacheResult: List<CacheData> = emptyList()
        val repository = object : IFirstPageMovieRepository {
            override fun getMovies(type: HomeMoviesType): List<CacheData> {
                return listCacheData
            }

            override suspend fun refreshMovieSection(type: HomeMoviesType): ResponseStatus {
                return ResponseStatus.Success
            }
        }
        val mockCallback: (HomeMovieDomain) -> Unit = {
            if (it is HomeMovieDomain.Success) {
                onSuccessCounter++
                cacheResult = it.movies
            }
        }

        useCase = FirstPageMoviesUC(repository)

        runBlocking {
            useCase.getMovies(HomeMoviesType.NOW_PLAYING, mockCallback)
        }

        assertEquals(onSuccessCounter, 2)
        assertEquals(cacheResult, listCacheData)
    }

    @Test
    fun `get movie on success from cache then error`() {
        val mockError = Randoms.alphaNumericString(10)

        var onSuccessCounter = 0
        var cacheResult: List<CacheData> = emptyList()
        var onErrorCounter = 0
        var errorResult = ""
        val repository = object : IFirstPageMovieRepository {
            override fun getMovies(type: HomeMoviesType): List<CacheData> {
                return listCacheData
            }

            override suspend fun refreshMovieSection(type: HomeMoviesType): ResponseStatus {
                return ResponseStatus.Error(mockError)
            }
        }
        val mockCallback: (HomeMovieDomain) -> Unit = {
            if (it is HomeMovieDomain.Success) {
                onSuccessCounter++
                cacheResult = it.movies
            } else if (it is HomeMovieDomain.Error) {
                onErrorCounter++
                errorResult = it.error
            }
        }

        useCase = FirstPageMoviesUC(repository)

        runBlocking {
            useCase.getMovies(HomeMoviesType.NOW_PLAYING, mockCallback)
        }

        assertEquals(onSuccessCounter, 1)
        assertEquals(onErrorCounter, 1)
        assertEquals(cacheResult, listCacheData)
        assertEquals(errorResult, mockError)
    }
}