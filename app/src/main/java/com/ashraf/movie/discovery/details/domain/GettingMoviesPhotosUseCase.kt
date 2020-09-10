package com.ashraf.movie.discovery.details.domain

import com.ashraf.movie.discovery.*
import com.ashraf.movie.discovery.details.data.MoviePhotosRepository
import io.reactivex.Single

class GettingMoviesPhotosUseCase(private val moviePhotosRepository: MoviePhotosRepository) {

    fun apply(title: String): Single<List<String>> =
        moviePhotosRepository.getMoviePhotos(title)
            .map { response ->
                val photosUrls: MutableList<String> = mutableListOf()
                val moviePhotoURlTemplate = FLICKR_PHOTO_URL_TEMPLATE
                    .replace(SERVER_KEY, it.server)
                    .replace(ID_KEY, it.id)
                    .replace(SECRET_KEY, it.secret)
                repeat(it.farm) { farm ->
                    photosUrls.add(
                        moviePhotoURlTemplate.replace(FARM_KEY, (farm + 1).toString())
                    )
                }
                photosUrls
            }
}