package com.ashraf.movie.discovery.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashraf.movie.discovery.SEARCH_YEAR_LIMIT
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>): Completable

    @Query("SELECT * FROM Movie $ORDER_CONDITION  LIMIT :offset, :pageSize")
    fun getMovies(offset: Int, pageSize: Int): Single<List<Movie>>

    @Query("SELECT count(*) FROM Movie")
    fun getCount(): Observable<Int>

    @Query("SELECT DISTINCT year FROM Movie WHERE title LIKE '%' || :text  || '%' ORDER BY year DESC")
    fun selectDistinctYearsSearchByTitle(text: String): Observable<List<Int>>

    @Query("SELECT * FROM Movie WHERE title  LIKE '%' || :text  || '%' AND year = :year LIMIT $SEARCH_YEAR_LIMIT")
    fun searchByTitleAndYearLimit(text: String, year: Int): Observable<List<Movie>>
}

const val ORDER_CONDITION: String = "ORDER By year DESC, rating DESC"
