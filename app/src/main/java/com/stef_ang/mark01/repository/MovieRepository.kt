package com.stef_ang.mark01.repository

import com.stef_ang.mark01.api.MovieService
import com.stef_ang.mark01.api.asCacheData
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.database.CacheData

// repository responsible to wrap data service (DB and remote)
// no LiveData or other android stuff
class MovieRepository(
    private val cacheDB: CacheDB,
    private val remoteService: MovieService
) {

    fun getMovies(): List<CacheData> {
        return cacheDB.cacheDao.getMovies()
    }

    suspend fun refreshMovieNowPlaying(): ResponseStatus {
        return try {
            val result = remoteService.getNowPlayingAsync(1)
            if (result.isSuccessful) {
                val nowPlaying = result.body()?.results ?: emptyList()
                cacheDB.cacheDao.insertAllCache(*nowPlaying.asCacheData())
            }
            ResponseStatus.Success
        } catch (e: Exception) {
            // nah untuk default error gini, krn ini repo, jauh di presentation layer, gbs pakai res String donk?
            ResponseStatus.Error(e.message ?: "Network Error")
        }
    }
}