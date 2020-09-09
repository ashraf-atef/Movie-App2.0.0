package com.ashraf.movie.common

import android.app.Application
import com.ashraf.movie.common.data.local.di.databaseModule
import com.ashraf.movie.common.data.remote.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }

    fun initKoin() {
        startKoin() {
            androidContext(baseContext)
            androidLogger()
            modules(listOf(
                databaseModule,
                remoteModule
            ))
        }
    }
}