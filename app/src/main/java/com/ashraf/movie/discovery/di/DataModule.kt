package com.ashraf.movie.discovery.di

import com.ashraf.movie.discovery.data.MoviesRepository
import com.ashraf.movie.discovery.data.local.MoviesLocalDataSource
import com.ashraf.movie.discovery.details.data.remote.MoviePhotosRemoteDataSource
import com.ashraf.movie.discovery.details.data.MoviePhotosRepository
import com.ashraf.movie.discovery.details.data.local.MoviePhotosLocalDataSource
import org.koin.dsl.module

val dataModule = module {
    factory { MoviesLocalDataSource(get()) }
    factory { MoviesRepository(get()) }

    factory { MoviePhotosRemoteDataSource(get()) }
    factory { MoviePhotosLocalDataSource(get()) }
    factory { MoviePhotosRepository(get(), get()) }

}