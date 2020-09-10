package com.ashraf.movie.discovery.details.domain

import com.ashraf.movie.discovery.*
import com.ashraf.movie.discovery.details.data.MoviePhotosRepository
import io.reactivex.Single

class GettingMoviesPhotosUseCase(private val moviePhotosRepository: MoviePhotosRepository) {

    fun apply(title: String): Single<List<String>> =
        moviePhotosRepository.getMoviePhotos(title)
            .map { response ->  response.photos.photo.map {
                    FLICKR_PHOTO_URL_TEMPLATE
                        .replace(FARM_KEY, it.farm.toString())
                        .replace(SERVER_KEY, it.server)
                        .replace(ID_KEY, it.id)
                        .replace(SECRET_KEY, it.secret)
                }
            }
}
