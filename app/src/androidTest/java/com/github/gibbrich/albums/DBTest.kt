package com.github.gibbrich.albums

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DBTest {
    private lateinit var db: AppDatabase
    private lateinit var albumDao: AlbumDao

    private val dbAlbums = listOf(
        DBAlbum(0, 0, "Queen II"),
        DBAlbum(1, 0, "Sheer Heart Attack"),
        DBAlbum(2, 0, "A Night at the Opera")
    )

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        albumDao = db.albumDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun getOrders_returns_empty_list_if_there_is_no_data_in_db() {
        albumDao.getOrders()
            .test()
            .assertValue {
                it.isEmpty()
            }
    }

    @Test
    fun getOrder_returns_orders_list_ordered_by_title() {
        albumDao.insert(dbAlbums)

        albumDao
            .getOrders()
            .test()
            .assertValue {
                it == dbAlbums.sortedBy(DBAlbum::title)
            }
    }
}