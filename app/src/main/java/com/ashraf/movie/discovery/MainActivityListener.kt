package com.ashraf.movie.discovery

import com.ashraf.movie.discovery.data.local.Movie

interface MainActivityListener {
    fun openMovieDetails(movie: Movie)
}