package com.ashraf.movie.discovery.data.local

import com.ashraf.movie.discovery.LOCAL_PAGE_SIZE
import io.reactivex.Observable
import io.reactivex.Single

class MoviesLocalDataSource(val movieDao: MovieDao) {

    fun getMovies(page: Int): Single<List<Movie>> =
        movieDao.getMovies(LOCAL_PAGE_SIZE * (page - 1), LOCAL_PAGE_SIZE);

    fun getCount(): Observable<Int> = movieDao.getCount()

    fun search(text: String): Observable<List<Movie>> =
        movieDao.selectDistinctYearsSearchByTitle(text)
            .flatMapIterable { years: List<Int> -> years}
            .flatMap { year ->
                movieDao.searchByTitleAndYearLimit(text, year)
            }

    fun getMovie(title: String) = movieDao.searchByTitle(title)
}
