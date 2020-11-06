package com.stef_ang.mark01.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CacheData::class], version = 1)
abstract class CacheDB : RoomDatabase() {
    abstract val cacheDao: CacheDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: CacheDB

        fun getDatabase(context: Context): CacheDB {
            synchronized(this) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CacheDB::class.java,
                        "cache_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
                return INSTANCE
            }
        }
    }
}