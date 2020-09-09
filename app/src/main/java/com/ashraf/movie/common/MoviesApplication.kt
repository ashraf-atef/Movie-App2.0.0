package com.ashraf.movie.common

import android.app.Application
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.MvRxViewModelConfigFactory
import com.ashraf.movie.common.data.local.di.databaseModule
import com.ashraf.movie.common.data.remote.di.remoteModule
import com.ashraf.movie.discovery.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        MvRx.viewModelConfigFactory = MvRxViewModelConfigFactory(applicationContext)
    }

    fun initKoin() {
        startKoin() {
            androidContext(baseContext)
            androidLogger()
            modules(
                listOf(
                    databaseModule,
                    remoteModule,
                    dataModule
                )
            )
        }
    }
}