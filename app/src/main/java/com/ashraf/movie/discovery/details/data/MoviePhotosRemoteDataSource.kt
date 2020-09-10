package com.ashraf.movie.discovery.details.data

import com.ashraf.movie.common.data.remote.RemoteAPI
import com.ashraf.movie.discovery.details.data.model.MoviesPhotosDetailsResponse
import io.reactivex.Single

class MoviePhotosRemoteDataSource(
    private val remoteAPI: RemoteAPI
) {
    fun getPhotos(title: String, page: Int): Single<MoviesPhotosDetailsResponse> =
        remoteAPI.getMoviePhotos(title, page)
}