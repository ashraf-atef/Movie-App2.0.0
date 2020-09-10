package com.ashraf.movie.common.data.remote

import com.ashraf.movie.BuildConfig
import com.ashraf.movie.discovery.REMOTE_PAGE_SIZE
import com.ashraf.movie.discovery.details.data.model.MoviesPhotosDetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteAPI {
    @GET("services/rest")
    fun getMoviePhotos(
        @Query("text") title: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = REMOTE_PAGE_SIZE,
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKEy: String = BuildConfig.PHOTOS_API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int= 1
    ) : Single<MoviesPhotosDetailsResponse>
}