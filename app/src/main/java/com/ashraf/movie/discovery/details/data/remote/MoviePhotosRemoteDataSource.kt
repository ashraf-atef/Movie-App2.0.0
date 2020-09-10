package com.ashraf.movie.discovery.details.data.remote

import com.ashraf.movie.common.data.remote.RemoteAPI
import com.ashraf.movie.discovery.details.data.model.MoviesPhotosResponse
import io.reactivex.Single

class MoviePhotosRemoteDataSource(
    private val remoteAPI: RemoteAPI
) {
    fun getPhotos(title: String, page: Int): Single<MoviesPhotosResponse> =
        remoteAPI.getMoviePhotos(title, page)
}