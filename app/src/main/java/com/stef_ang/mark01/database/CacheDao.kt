package com.stef_ang.mark01.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stef_ang.mark01.database.entity.MovieDO
import com.stef_ang.mark01.database.entity.MovieDetailDO

@Dao
interface CacheDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): LiveData<List<MovieDO>>

    @Query("SELECT * FROM movie")
    fun getMovies2(): List<MovieDO>

    @Query("SELECT * FROM movie_detail WHERE id = :id")
    fun getMovieDetail(id: Int): LiveData<MovieDetailDO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMovie(vararg movies: MovieDO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movie: MovieDetailDO)

    @Query("DELETE FROM movie")
    fun invalidate()
}