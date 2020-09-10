package com.ashraf.movie.discovery.details.data.local

import com.ashraf.movie.discovery.LOCAL_PAGE_SIZE
import com.ashraf.movie.discovery.PHOTOS_PAGE_SIZE
import com.ashraf.movie.discovery.details.data.model.MoviePhotoDetails
import io.reactivex.Completable
import io.reactivex.Single

class MoviePhotosLocalDataSource(private val photosDao: PhotosDao) {

    fun insert(photos: List<MoviePhotoDetails>): Completable = photosDao.insert(photos)

    fun getPhotos(title: String, page: Int) : Single<List<MoviePhotoDetails>> =
        photosDao.getPhotos(title, PHOTOS_PAGE_SIZE * (page - 1), PHOTOS_PAGE_SIZE)
}