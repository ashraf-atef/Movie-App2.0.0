package com.ashraf.movie.discovery.data

import com.ashraf.movie.discovery.data.local.Movie
import com.ashraf.movie.discovery.data.local.MoviesLocalDataSource
import io.reactivex.Observable
import io.reactivex.Single

class MoviesRepository(private val moviesLocalDataSource: MoviesLocalDataSource) {
    private var pageNumber = 1

    fun getMovies(): Single<List<Movie>> = moviesLocalDataSource.getMovies(pageNumber)
        .doOnSuccess { pageNumber++ }

    fun getCount(): Observable<Int> = moviesLocalDataSource.getCount()

    fun search(text: String): Observable<List<Movie>> = moviesLocalDataSource.search(text)

    fun getMovie(title: String) = moviesLocalDataSource.getMovie(title)
}