package com.github.gibbrich.albums

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Test

class AlbumsDataRepositoryTest {
    private val dbAlbums = listOf(
        DBAlbum(0, 0, "Queen II"),
        DBAlbum(1, 0, "Sheer Heart Attack"),
        DBAlbum(2, 0, "A Night at the Opera")
    )

    private val dbAlbums2 = listOf(
        DBAlbum(3, 4, "Queen"),
        DBAlbum(4, 4, "Flash Gordon")
    )

    private val nwAlbums = listOf(
        NWAlbum(0, 1, "News of the World"),
        NWAlbum(1, 1, "Jazz"),
        NWAlbum(2, 1, "The Game")
    )

    @Test
    fun `getAlbums returns data in sorted order from Api if there is no data in cache or DB`() {
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.albumDao().getOrders() } returns Single.just(emptyList())

        val api = mockk<Api>()
        every { api.getAlbums() } returns Single.just(nwAlbums)

        val repository = AlbumsDataRepository(db, api)

        repository.getAlbums()
            .test()
            .assertValue {
                val list = nwAlbums.map(AlbumConverter::fromNetwork).sortedBy(Album::title)
                it == list
            }
            .assertComplete()
    }

    @Test
    fun `getAlbums returns data in sorted order from DB if there is no data in cache`() {
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.albumDao().getOrders() } returns Single.just(dbAlbums.sortedBy(DBAlbum::title))

        val api = mockk<Api>()

        val repository = AlbumsDataRepository(db, api)

        repository
            .getAlbums()
            .test()
            .assertValue {
                val list = dbAlbums.map(AlbumConverter::fromDB).sortedBy(Album::title)
                it == list
            }
            .assertComplete()
    }

    @Test
    fun `getAlbums fetches data from network if DB is empty and caches it`() {
        // fill cache with data from api
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.albumDao().getOrders() } returns Single.just(emptyList())

        val api = mockk<Api>()
        every { api.getAlbums() } returns Single.just(nwAlbums)

        val repository = AlbumsDataRepository(db, api)

        repository
            .getAlbums()
            .test()

        // turn off api calls
        every { api.getAlbums() } returns Single.just(emptyList())

        repository
            .getAlbums()
            .test()
            .assertValue {
                val list = nwAlbums.map(AlbumConverter::fromNetwork).sortedBy(Album::title)
                it == list
            }
            .assertComplete()
    }

    @Test
    fun `getAlbums fetches data from DB if it is not empty and caches it`() {
        // fill cache with data from db
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.albumDao().getOrders() } returns Single.just(dbAlbums.sortedBy(DBAlbum::title))

        val api = mockk<Api>()
        every { api.getAlbums() } returns Single.just(emptyList())

        val repository = AlbumsDataRepository(db, api)

        repository
            .getAlbums()
            .test()

        // turn off db calls
        every { db.albumDao().getOrders() } returns Single.just(emptyList())

        repository
            .getAlbums()
            .test()
            .assertValue {
                val list = dbAlbums.map(AlbumConverter::fromDB).sortedBy(Album::title)
                it == list
            }
            .assertComplete()
    }

    @Test
    fun `getAlbums(true) clears cache, fetches data from DB if it is not empty and caches it`() {
        // fill cache with data from db
        val db = mockk<AppDatabase>(relaxed = true)
        every { db.albumDao().getOrders() } returns Single.just(dbAlbums.sortedBy(DBAlbum::title))

        val api = mockk<Api>()
        every { api.getAlbums() } returns Single.just(emptyList())

        val repository = AlbumsDataRepository(db, api)

        repository
            .getAlbums()
            .test()

        // change data in db
        every { db.albumDao().getOrders() } returns Single.just(dbAlbums2.sortedBy(DBAlbum::title))

        // call repository.getAlbums(false) cache returns old data
        repository
            .getAlbums()
            .test()
            .assertValue {
                val list = dbAlbums.map(AlbumConverter::fromDB).sortedBy(Album::title)
                it == list
            }
            .assertComplete()

        // call repository.getAlbums(true), returns new data
        repository
            .getAlbums(true)
            .test()
            .assertValue {
                val list = dbAlbums2.map(AlbumConverter::fromDB).sortedBy(Album::title)
                it == list
            }
            .assertComplete()

        // turn off db calls
        every { db.albumDao().getOrders() } returns Single.just(emptyList())

        // call repository.getAlbums(false) cache returns new cached data
        repository
            .getAlbums()
            .test()
            .assertValue {
                val list = dbAlbums2.map(AlbumConverter::fromDB).sortedBy(Album::title)
                it == list
            }
            .assertComplete()
    }
}