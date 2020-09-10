package com.ashraf.movie.discovery.details

import com.airbnb.mvrx.*
import com.ashraf.movie.discovery.data.MoviesRepository
import com.ashraf.movie.discovery.data.local.Movie
import com.ashraf.movie.discovery.details.domain.GettingMoviesPhotosUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.get

class MovieDetailsViewModel(
    initialState: MovieDetailsState,
    private val moviesRepository: MoviesRepository,
    private val gettingMoviesPhotosUseCase: GettingMoviesPhotosUseCase
) : BaseMvRxViewModel<MovieDetailsState>(initialState) {

    init {
        getMovie()
        getPhotos()
    }

    fun getMovie() {
        withState { state ->
            moviesRepository.getMovie(state.title)
                .subscribeOn(Schedulers.io())
                .execute {
                    copy(
                        movie = it
                    )
                }
        }
    }

    fun getPhotos() {
        withState { state ->
            if (state.photosPageRequest is Loading)
                return@withState

            gettingMoviesPhotosUseCase.apply(state.title)
                .subscribeOn(Schedulers.io())
                .execute {
            val newPhotos: List<String> = if (it is Success) (it)()!! else listOf()
                    copy(
                        photos = photos + newPhotos,
                        photosPageRequest = it
                    )
                }
        }
    }

    companion object : MvRxViewModelFactory<MovieDetailsViewModel, MovieDetailsState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: MovieDetailsState
        ): MovieDetailsViewModel? {
            with(viewModelContext.activity) {
                return MovieDetailsViewModel(
                    state,
                    get(),
                    get()
                )
            }
        }
    }
}