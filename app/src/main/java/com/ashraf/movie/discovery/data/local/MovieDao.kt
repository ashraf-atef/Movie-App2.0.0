package com.ashraf.movie.discovery.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>): Completable

    @Query("SELECT * FROM Movie ORDER By year, rating DESC LIMIT :offset, :pageSize")
    fun getMovies(offset: Int, pageSize: Int): Single<List<Movie>>

    @Query("SELECT count(*) FROM Movie")
    fun getCount(): Observable<Int>
}