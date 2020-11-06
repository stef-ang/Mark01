package com.stef_ang.mark01.database

import androidx.annotation.StringDef
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache_data")
data class CacheData(
    @PrimaryKey
    val id: Int,
    @DataType val type: String,
    val serializedObject: String
) {

    companion object {
        const val TYPE_MOVIE = "movie"
        const val TYPE_MOVIE_DETAIL = "movie_detail"

        @Retention
        @StringDef(TYPE_MOVIE)
        annotation class DataType
    }
}