package com.stef_ang.mark01.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CacheDao {

    @Query("SELECT * FROM cache_data WHERE type = :type")
    fun getMovies(type: String = CacheData.TYPE_MOVIE): List<CacheData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCache(vararg cacheData: CacheData)

    @Query("DELETE FROM cache_data")
    fun invalidate()
}