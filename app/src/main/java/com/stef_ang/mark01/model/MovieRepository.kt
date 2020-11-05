package com.stef_ang.mark01.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.stef_ang.mark01.api.Api
import com.stef_ang.mark01.api.asDatabaseModel
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.database.asDomainModel
import com.stef_ang.mark01.database.entity.MovieDO
import com.stef_ang.mark01.domain.HomeMovie
import java.net.SocketException

class MovieRepository(private val cacheDB: CacheDB) {

    // Movies that can be shown on the screen.
    // Key concept: Always show data from DB
    val movies: LiveData<List<HomeMovie>> = Transformations.map(cacheDB.cacheDao.getMovies()) {
        it.asDomainModel()
    }

    fun getMovies(): List<MovieDO> {
        return cacheDB.cacheDao.getMovies2()
    }

    suspend fun refreshMovieNowPlaying(): Pair<Boolean, String> {
        try {
            val result = Api.retrofitService.getNowPlayingAsync(1)
            if (result.isSuccessful) {
                val nowPlaying = result.body()?.results ?: emptyList()
                cacheDB.cacheDao.insertAllMovie(*nowPlaying.asDatabaseModel())
            }
            return result.isSuccessful to ""
        } catch (e: Exception) {
            return false to (e.message ?: "Error Network")
        }
    }
}