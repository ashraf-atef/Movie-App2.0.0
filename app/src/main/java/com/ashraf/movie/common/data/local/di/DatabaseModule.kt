package com.ashraf.movie.common.data.local.di

import com.ashraf.movie.common.data.local.LocalDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { LocalDatabase.getInstance(get()) }
    single { get<LocalDatabase>().moviesDao() }
    single { get<LocalDatabase>().photosDao() }
}