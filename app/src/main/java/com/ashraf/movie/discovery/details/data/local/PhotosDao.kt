package com.ashraf.movie.discovery.details.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashraf.movie.discovery.details.data.model.MoviePhotoDetails
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<MoviePhotoDetails>): Completable

    @Query("SELECT * FROM Photos WHERE movieTitle = :title LIMIT :offset, :pageSize ")
    fun getPhotos(title: String, offset: Int, pageSize: Int): Single<List<MoviePhotoDetails>>
}