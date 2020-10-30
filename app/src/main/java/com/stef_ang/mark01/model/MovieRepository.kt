package com.stef_ang.mark01.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.stef_ang.mark01.api.Api
import com.stef_ang.mark01.api.asDatabaseModel
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.database.asDomainModel
import com.stef_ang.mark01.domain.HomeMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val cacheDB: CacheDB) {

    // Movies that can be shown on the screen.
    // Key concept: Always show data from DB
    val movies: LiveData<List<HomeMovie>> = Transformations.map(cacheDB.cacheDao.getMovies()) {
        it.asDomainModel()
    }

    suspend fun refreshMovieNowPlaying() {
        withContext(Dispatchers.IO) {
            val result = Api.retrofitService.getNowPlayingAsync(1).await()
            val nowPlaying = result.results ?: emptyList()
            cacheDB.cacheDao.insertAll(*nowPlaying.asDatabaseModel())
        }
    }
}