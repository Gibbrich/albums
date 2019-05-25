package com.github.gibbrich.data.di

import com.github.gibbrich.core.repository.AlbumsRepository
import dagger.Component

@DataScope
@Component(modules = [
    ApiModule::class,
    DBModule::class,
    DataModule::class
])
interface DataComponent {
    val albumsRepository: AlbumsRepository
}