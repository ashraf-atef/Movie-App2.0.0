package com.ashraf.movie.discovery.details

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.ashraf.movie.discovery.data.local.Movie

data class MovieDetailsState(
    val title: String,
    val photos: List<String> = listOf(),
    val photosPageRequest: Async<List<String>> = Uninitialized,
    val movie: Async<Movie> = Uninitialized
): MvRxState {
    constructor(args: MovieArgs) : this(args.title)
}