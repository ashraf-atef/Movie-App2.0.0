package com.ashraf.movie.discovery.movies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.AutoModel
import com.airbnb.mvrx.*
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
                    (when {
                        it.filterText.isEmpty() -> it.movies
                        it.filteredMoviesRequest is Success -> (it.filteredMoviesRequest)()
                        else -> listOf()
                    })
                        .forEach { item: Movie ->
                            movie {
                                id("movie", item.title)
                                movie(item)
                                spanSizeOverride { _, _, _ -> 1 }
                            }
                        }

                    // Add loading if loading
                    if (it.moviesPageRequest is Loading || it.filteredMoviesRequest is Loading)
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

        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                moviesViewModel.onSearch(s.toString())
            }

        })

        iv_clear.setOnClickListener { et_search.text.clear() }
    }

    override fun invalidate() {
        moviesController.requestModelBuild()
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}