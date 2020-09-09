package com.ashraf.movie.discovery.movies

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.ashraf.movie.discovery.data.local.Movie

data class MoviesState(
    /**
     * A list that presents all movies displayed in the screen
     */
    val movies: List<Movie> = listOf(),
    /**
     * A list presents the filtered list after the search or any filters
     */
    val filteredMovies: List<Movie> = listOf(),
    /**
     * The text of filter or search
     */
    val filterText: String = "",
    /**
     * Async object that presents each request for getting movies page by page
     */
    val moviesPageRequest: Async<List<Movie>> = Uninitialized,
    /**
     * Async object that present each request for filtering or searching page by page
     */
    val filteredMoviesRequest: Async<List<Movie>> = Uninitialized,
    /**
     * Flag that shows inserting seed on progress
     */
    val insertingSeedsOnProgress: Boolean = true
) : MvRxState
