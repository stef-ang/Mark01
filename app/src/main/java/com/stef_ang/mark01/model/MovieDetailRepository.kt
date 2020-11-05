package com.stef_ang.mark01.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.stef_ang.mark01.api.Api
import com.stef_ang.mark01.api.asDatabaseModel
import com.stef_ang.mark01.database.CacheDB
import com.stef_ang.mark01.database.asDomainModel
import com.stef_ang.mark01.domain.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailRepository(
    private val id: Int,
    private val cacheDB: CacheDB
) {

    val movieDetail: LiveData<MovieDetail> = Transformations.map(cacheDB.cacheDao.getMovieDetail(id)) {
        it.asDomainModel()
    }

    suspend fun refreshMovieDetail() {
        withContext(Dispatchers.IO) {
            val result = Api.retrofitService.getMovieDetail(id).await()
            cacheDB.cacheDao.insertMovieDetail(result.asDatabaseModel())
        }
    }
}