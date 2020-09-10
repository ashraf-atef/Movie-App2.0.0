package com.ashraf.movie.discovery.di

import com.ashraf.movie.discovery.details.domain.GettingMoviesPhotosUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GettingMoviesPhotosUseCase(get()) }
}