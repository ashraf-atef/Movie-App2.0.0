package com.ashraf.movie.discovery.di

import com.ashraf.movie.discovery.data.MoviesRepository
import com.ashraf.movie.discovery.data.local.MoviesLocalDataSource
import org.koin.dsl.module

val dataModule = module {
    factory { MoviesLocalDataSource(get()) }
    factory { MoviesRepository(get()) }
}