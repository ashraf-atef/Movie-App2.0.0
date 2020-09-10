package com.ashraf.movie.discovery.data.local

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
class Movie (
    @PrimaryKey
    @NonNull
    val title: String,
    val year: Int,
    val cast: ArrayList<String>,
    val genres: ArrayList<String>,
    val rating: Double
)
