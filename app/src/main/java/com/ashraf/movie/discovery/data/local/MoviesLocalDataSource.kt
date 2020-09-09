package com.ashraf.movie.discovery.data.local

import com.ashraf.movie.discovery.PAGE_SIZE
import io.reactivex.Observable
import io.reactivex.Single

class MoviesLocalDataSource(val movieDao: MovieDao) {

    fun getMovies(page: Int): Single<List<Movie>> =
        movieDao.getMovies(PAGE_SIZE * page - 1, PAGE_SIZE);

    fun getCount(): Observable<Int> = movieDao.getCount()

    fun search(text: String): Single<List<Movie>> = movieDao.searchByTitle(text)
}