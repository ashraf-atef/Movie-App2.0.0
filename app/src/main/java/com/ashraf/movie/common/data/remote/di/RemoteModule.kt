package com.ashraf.movie.common.data.remote.di

import com.ashraf.movie.common.data.remote.NetworkFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.math.sin

const val API_URL_KEY = "api_url"
val remoteModule = module {
    single(named(API_URL_KEY)) { NetworkFactory.provideAPIURL() }
    single { NetworkFactory.providesOkHttpClient() }
    single { NetworkFactory.providesGson() }
    single { NetworkFactory.providesRetrofit(get(named(API_URL_KEY)), get(), get()) }
    single { NetworkFactory.providesApi(get()) }
}
