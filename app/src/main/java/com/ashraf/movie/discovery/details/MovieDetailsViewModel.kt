package com.ashraf.movie.discovery.details

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.ashraf.movie.discovery.data.MoviesRepository
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

            gettingMoviesPhotosUseCase.apply(state.title)
                .subscribeOn(Schedulers.io())
                .execute {
                    copy(
                        photos = it
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