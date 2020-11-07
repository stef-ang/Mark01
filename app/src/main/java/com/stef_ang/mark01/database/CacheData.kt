package com.stef_ang.mark01.database

import androidx.annotation.StringDef
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache_data")
data class CacheData(
    val id: Int,
    @PrimaryKey
    val key: String,
    @DataType val type: String,
    @ColumnInfo(name = "custom_order") val order: Int,
    val serializedObject: String
) {

    companion object {
        const val TYPE_HOME_NOW_PLAYING = "home_now_playing"
        const val TYPE_HOME_POPULAR = "home_popular"
        const val TYPE_HOME_UPCOMING = "home_upcoming"

        @Retention
        @StringDef(TYPE_HOME_NOW_PLAYING, TYPE_HOME_POPULAR, TYPE_HOME_UPCOMING)
        annotation class DataType
    }
}