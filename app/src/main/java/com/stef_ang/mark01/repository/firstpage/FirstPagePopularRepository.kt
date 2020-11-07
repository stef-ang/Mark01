package com.stef_ang.mark01.repository.firstpage

import com.stef_ang.mark01.api.MovieService
import com.stef_ang.mark01.api.asCacheData
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.database.CacheData
import com.stef_ang.mark01.repository.ResponseStatus
import javax.inject.Inject

class FirstPagePopularRepository @Inject constructor(
    private val cacheDB: CacheDB,
    private val remoteService: MovieService
): FirstPageMovieRepository {

    override val types: String = CacheData.TYPE_HOME_POPULAR

    override fun getMovies(): List<CacheData> {
        return cacheDB.cacheDao.getHomeSectionMovies(types)
    }

    override suspend fun refreshMovieSection(): ResponseStatus {
        return try {
            val result = remoteService.getPopularAsync(1)
            if (result.isSuccessful) {
                val nowPlaying = result.body()?.results ?: emptyList()
                cacheDB.cacheDao.insertAllCache(*nowPlaying.asCacheData(types))
            }
            ResponseStatus.Success
        } catch (e: Exception) {
            ResponseStatus.Error(e.message ?: "Network Error")
        }
    }
}