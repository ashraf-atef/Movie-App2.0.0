package com.ashraf.movie.discovery.movies

import android.util.Log
import com.airbnb.mvrx.*
import com.ashraf.movie.discovery.data.MoviesRepository
import com.ashraf.movie.discovery.data.local.Movie
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.get
import java.util.concurrent.TimeUnit

class MoviesViewModel(
    initialState: MoviesState,
    private val moviesRepository: MoviesRepository
) : BaseMvRxViewModel<MoviesState>(initialState) {

    init {
        listenUntilAnyRecordsInserted()
    }

    private lateinit var moviesCounterDisposable: Disposable

    private fun listenUntilAnyRecordsInserted() {
        moviesCounterDisposable = moviesRepository.getCount()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("SEED", "Counter: $it")
                if (it > 0) {
                    if (!moviesCounterDisposable.isDisposed)
                        moviesCounterDisposable.dispose()
                    getMovies()
                }
            }
    }

    fun getMovies() {
        moviesRepository.getMovies()
            .subscribeOn(Schedulers.io())
            .execute {
                val newMovies: List<Movie> = if (it is Success) (it)()!! else listOf()
                copy(
                    insertingSeedsOnProgress = false,
                    movies = (movies + newMovies),
                    moviesPageRequest = it
                )
            }
    }

    fun onSearch(text: String) {
        setState {
            copy(
                filterText = text,
                filteredMoviesRequest = Uninitialized
            )
        }
        if (text.isNotEmpty())
            moviesRepository.search(text)
                .subscribeOn(Schedulers.io())
                .execute {
                    copy(
                        filteredMoviesRequest = if (it is Success) 
                    )
                }
    }

    companion object : MvRxViewModelFactory<MoviesViewModel, MoviesState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: MoviesState
        ): MoviesViewModel? {
            with(viewModelContext.activity) {
                return MoviesViewModel(
                    state,
                    get()
                )
            }
        }
    }

}