package com.ashraf.movie.common.data.local

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ashraf.movie.R
import com.ashraf.movie.common.data.local.di.MoviesSeed
import com.ashraf.movie.discovery.data.local.Movie
import com.ashraf.movie.discovery.data.local.MovieDao
import com.ashraf.movie.discovery.details.data.local.PhotosDao
import com.ashraf.movie.discovery.details.data.model.MoviePhotoDetails
import com.google.gson.Gson
import io.reactivex.Observable
import java.io.InputStream
import java.util.concurrent.TimeUnit

@Database(
    entities = [Movie::class, MoviePhotoDetails::class],
    version = 1
)
@TypeConverters(value = [Converters::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao

    abstract fun photosDao(): PhotosDao

    companion object {

        lateinit var instance: LocalDatabase

        fun getInstance(context: Context): LocalDatabase {
            synchronized(LocalDatabase::class) {
                if (!::instance.isInitialized) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        "movies-database.db"
                    )
                        .addCallback(seedDatabaseCallback(context))
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }

        private fun seedDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    var movieDao = getInstance(context).moviesDao()

                    val res: Resources = context.resources
                    val inputStream: InputStream = res.openRawResource(R.raw.movies)

                    val byteArray = ByteArray(inputStream.available())
                    inputStream.read(byteArray)
                    val moviesStr = String(byteArray)
                    val moviesSeed: MoviesSeed = Gson().fromJson(moviesStr, MoviesSeed::class.java)

                    Observable.fromIterable(
                        moviesSeed
                            .movies
                            .sortedByDescending { it.year }
                            .groupBy { it.year }
                            .values
                    )
                        .delay(1, TimeUnit.SECONDS)
                        .flatMapCompletable {
                            movieDao.insert(it)
                                .doOnComplete() {
                                    Log.d("SEED", "Year ${it.first().year} inserted")
                                }
                                .doOnError {
                                    it.printStackTrace()
                                    Log.d("SEED", "Error: ${it.message}")
                                }
                        }
                        .subscribe()

                }
            }
        }
    }
}