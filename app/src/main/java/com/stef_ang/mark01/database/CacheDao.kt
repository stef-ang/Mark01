package com.stef_ang.mark01.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stef_ang.mark01.database.entity.MovieDO

@Dao
interface CacheDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): LiveData<List<MovieDO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movies: MovieDO)

    @Query("DELETE FROM movie")
    fun invalidate()
}