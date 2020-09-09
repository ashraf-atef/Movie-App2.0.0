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

    @Query(
        "SELECT DISTINCT year FROM Movie AS first left join " +
                " SELECT * from Movie as second where first.year = second.year" +
                " AND title like '%:text' AND year = :year $ORDER_CONDITION LIMIT $SEARCH_YEAR_LIMIT"
    )
    fun searchByTitle(text: String): Single<List<Movie>>
}

const val ORDER_CONDITION: String = "ORDER By year, rating DESC"
