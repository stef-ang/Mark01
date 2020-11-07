package com.stef_ang.mark01.database

import androidx.annotation.StringDef
import androidx.room.Entity

@Entity(tableName = "cache_data", primaryKeys = ["id", "type"])
data class CacheData(
    val id: Int,
    @DataType val type: String,
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