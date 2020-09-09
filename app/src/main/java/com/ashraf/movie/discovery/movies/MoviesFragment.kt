package com.ashraf.movie.discovery.movies

import android.os.Bundle
import android.view.View
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.AutoModel
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.ashraf.movie.R
import com.ashraf.movie.discovery.data.local.Movie
import com.ashraf.movie.discovery.movies.epoxy.LoadingModelModel_
import com.ashraf.movie.discovery.movies.epoxy.loadingModel
import com.ashraf.movie.discovery.movies.epoxy.movie
import com.example.epoxyexample.common.EndlessRecyclerViewOnScrollListener
import kotlinx.android.synthetic.main.fragment_movies_fragmment.*

class MoviesFragment : BaseMvRxFragment(R.layout.fragment_movies_fragmment) {

    private val moviesViewModel: MoviesViewModel by fragmentViewModel()
    private val moviesController by lazy {
        object : AsyncEpoxyController() {

            override fun buildModels() {
                withState(moviesViewModel) {
                    // Add Movies
                    (if (it.filterText.isEmpty()) it.movies else it.filteredMovies)
                        .forEach { item: Movie ->
                            movie {
                                id("movie", item.title)
                                movie(item)
                                spanSizeOverride { _, _, _ -> 1 }
                            }
                        }

                    // Add loading if loading
                    if(it.moviesPageRequest is Loading || it.filteredMoviesRequest is Loading)
                        loadingModel {
                            id("loading")
                        }

                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_movies.setController(moviesController)

        rv_movies.addOnScrollListener(object : EndlessRecyclerViewOnScrollListener() {
            override fun onLoadMore() {
                moviesViewModel.getMovies()
            }
        })
    }

    override fun invalidate() {
        moviesController.requestModelBuild()
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}