package com.stef_ang.mark01.repository

import com.stef_ang.mark01.api.MovieService
import com.stef_ang.mark01.api.asCacheData
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.util.HomeMoviesType
import javax.inject.Inject

class FirstPageMovieRepository @Inject constructor(
    private val cacheDB: CacheDB,
    private val remoteService: MovieService
): IFirstPageMovieRepository {

    override fun getMovies(type: HomeMoviesType): List<CacheData> {
        return cacheDB.cacheDao.getHomeSectionMovies(type.cacheType)
    }

    override suspend fun refreshMovieSection(type: HomeMoviesType): ResponseStatus {
        return try {
            val result = remoteService.getMoviesAsync(type.pathType, 1)
            if (result.isSuccessful) {
                val movies = result.body()?.results ?: emptyList()
                cacheDB.cacheDao.insertAllCache(*movies.asCacheData(type.cacheType))
            }
            ResponseStatus.Success
        } catch (e: Exception) {
            ResponseStatus.Error(e.message ?: "Network Error")
        }
    }
}