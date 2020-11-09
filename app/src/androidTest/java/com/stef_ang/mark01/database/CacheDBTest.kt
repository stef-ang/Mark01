package com.stef_ang.mark01.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CacheDBTest {

    private lateinit var cacheDao: CacheDao
    private lateinit var db: CacheDB

    // region dummy data
    private val data1 by lazy {
        CacheData(
            1,
            "1_${CacheData.TYPE_HOME_UPCOMING}",
            CacheData.TYPE_HOME_UPCOMING,
            0,
            "dfbvhdsbvdbhjds vbjsdbvjksdv"
        )
    }
    private val data2 by lazy {
        CacheData(
            2,
            "2_${CacheData.TYPE_HOME_POPULAR}",
            CacheData.TYPE_HOME_POPULAR,
            10,
            "sdgnkdf gdgb dbkjdb vhbd"
        )
    }
    private val data3 by lazy {
        CacheData(
            3,
            "3_${CacheData.TYPE_HOME_POPULAR}",
            CacheData.TYPE_HOME_POPULAR,
            9,
            "shdbjdhfgb hjbvdssd svnd"
        )
    }
    // endregion

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, CacheDB::class.java)
            .allowMainThreadQueries()
            .build()
        cacheDao = db.cacheDao
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun testInsertAndGetCache() {
        // given
        cacheDao.insertAllCache(data1, data2, data3)

        // when
        val result1 = cacheDao.getHomeSectionMovies(CacheData.TYPE_HOME_UPCOMING)
        // then
        assertEquals(result1.size, 1)
        assertEquals(result1.first().key, data1.key)
        assertEquals(result1.first().serializedObject, data1.serializedObject)

        // when
        val result2 = cacheDao.getHomeSectionMovies(CacheData.TYPE_HOME_POPULAR)
        // then
        assertEquals(result2.size, 2)
        assertEquals(result2.first().key, data3.key)
        assertEquals(result2.first().serializedObject, data3.serializedObject)
        assertEquals(result2[1].key, data2.key)
        assertEquals(result2[1].serializedObject, data2.serializedObject)
    }

    @Test
    fun testInsertThenInvalidate() {
        cacheDao.insertAllCache(data1, data2, data3)

        // when
        cacheDao.invalidate()

        val result1 = cacheDao.getHomeSectionMovies(CacheData.TYPE_HOME_UPCOMING)
        assertEquals(result1.size, 0)

        val result2 = cacheDao.getHomeSectionMovies(CacheData.TYPE_HOME_POPULAR)
        assertEquals(result2.size, 0)
    }
}