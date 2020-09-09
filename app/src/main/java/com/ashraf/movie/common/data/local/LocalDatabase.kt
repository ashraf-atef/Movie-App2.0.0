package com.ashraf.movie.common.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ashraf.movie.discovery.data.local.Movie
import com.ashraf.movie.discovery.data.local.MovieDao


@Database(
    entities = [Movie::class],
    version = 1
)
@TypeConverters(value = [Converters::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao


    companion object {

        lateinit var instance: LocalDatabase
        fun getInstance(context: Context): LocalDatabase {
            synchronized(LocalDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java, "movies-database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                return instance
            }
        }
    }
}