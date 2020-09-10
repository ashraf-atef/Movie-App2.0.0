package com.ashraf.movie.discovery.details.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Photos")
data class MoviePhotoDetails(
    @PrimaryKey
    @NonNull
    val id: String,
    @Transient
    @ColumnInfo(name = "movieTitle")
    val movieTitle: String = "",
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val isPublic: Int,
    val isFriend: Int,
    val isFamily: Int
)