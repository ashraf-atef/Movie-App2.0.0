package com.ashraf.movie.discovery.details.data

import com.ashraf.movie.discovery.details.data.model.MoviesPhotosDetailsResponse
import io.reactivex.Single

class MoviePhotosRepository(
    private val moviePhotosRemoteDataSource: MoviePhotosRemoteDataSource
) {
    var page = 1
    fun getMoviePhotos(title: String): Single<MoviesPhotosDetailsResponse> =
        moviePhotosRemoteDataSource.getPhotos(title, page)
            .doOnSuccess { page++ }
}