package com.ashraf.movie.discovery.details.data

import com.ashraf.movie.discovery.details.data.local.MoviePhotosLocalDataSource
import com.ashraf.movie.discovery.details.data.model.MoviePhotoDetails
import com.ashraf.movie.discovery.details.data.model.MoviesPhotos
import com.ashraf.movie.discovery.details.data.model.MoviesPhotosResponse
import com.ashraf.movie.discovery.details.data.remote.MoviePhotosRemoteDataSource
import io.reactivex.Single

class MoviePhotosRepository(
    private var moviePhotosLocalDataSource: MoviePhotosLocalDataSource,
    private val moviePhotosRemoteDataSource: MoviePhotosRemoteDataSource
) {
    var page = 1
    fun getMoviePhotos(title: String): Single<List<MoviePhotoDetails>> =
        moviePhotosLocalDataSource.getPhotos(title, page)
            .flatMap { localResponse ->
                if (localResponse.isEmpty())
                // Get from remote
                    moviePhotosRemoteDataSource.getPhotos(title, page)
                        .flatMap { remoteResponse ->
                            moviePhotosLocalDataSource.insert(
                                remoteResponse.photos.photo
                                    .map {
                                        it.copy(movieTitle = title)
                                    }
                            ).andThen(Single.just(remoteResponse))
                        }
                        .map { it.photos.photo }
                else
                // Return the same local response
                    Single.just(localResponse)
            }
            .doOnSuccess { page++ }
}