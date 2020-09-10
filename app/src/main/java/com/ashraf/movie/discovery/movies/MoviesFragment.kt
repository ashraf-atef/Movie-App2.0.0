package com.ashraf.movie.discovery.movies

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.ashraf.movie.R
import com.ashraf.movie.common.presentation.epoxy.loadingModel
import com.ashraf.movie.discovery.MainActivityListener
import com.ashraf.movie.discovery.data.local.Movie
import com.ashraf.movie.discovery.details.MovieDetailsFragment
import com.ashraf.movie.discovery.movies.epoxy.movie
import com.example.epoxyexample.common.EndlessRecyclerViewOnScrollListener
import kotlinx.android.synthetic.main.fragment_movies.*
import java.lang.IllegalArgumentException

class MoviesFragment : BaseMvRxFragment(R.layout.fragment_movies) {

    private val moviesViewModel: MoviesViewModel by fragmentViewModel()
    private lateinit var mainActivityListener: MainActivityListener
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
                                movieClickListener(View.OnClickListener {
                                    mainActivityListener.openMovieDetails(item)
                                })
                                spanSizeOverride { _, _, _ -> 1 }
                            }
                        }

                    // Add loading if is loading
                    if (it.moviesPageRequest is Loading || it.filteredMoviesRequest is Loading)
                        loadingModel {
                            id("loading")
                        }

                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityListener)
            mainActivityListener = context
        else
            throw IllegalArgumentException("Activity that opens this fragment should implement MainActivityListener")
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