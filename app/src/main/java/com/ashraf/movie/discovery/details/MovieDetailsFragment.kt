package com.ashraf.movie.discovery.details

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.mvrx.*
import com.ashraf.movie.R
import com.ashraf.movie.common.presentation.epoxy.loadingModel
import com.ashraf.movie.discovery.details.epoxy.photo
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_movie_details.*

@Parcelize
data class MovieArgs(
    val title: String
) : Parcelable

class MovieDetailsFragment : BaseMvRxFragment(R.layout.fragment_movie_details) {

    private val movieDetailsViewModel: MovieDetailsViewModel by fragmentViewModel()

    private val photosController by lazy {
        object : AsyncEpoxyController() {

            override fun buildModels() {
                withState(movieDetailsViewModel) {
                    // Add photos
                    when (it.photos) {
                        is Success -> (it.photos)().forEach { url ->
                            photo {
                                id("photo", url)
                                photoUrl(url)
                                spanSizeOverride { _, _, _ -> 1 }
                            }
                        }
                        is Error -> Toast.makeText(
                            requireContext(),
                            getString(R.string.msg_error),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    // Add loading if is loading
                    if (it.photos is Loading)
                        loadingModel {
                            id("loading")
                        }
                }
            }
        }
    }

    override fun invalidate() {
        withState(movieDetailsViewModel) {
            if (it.movie is Success) {
                with((it.movie)()) {
                    tv_movie_details.text = StringBuilder()
                        .appendln(year)
                        .appendln(genres.joinToString(separator = "\n"))
                        .appendln(cast.joinToString(separator = "\n"))
                        .toString()
                }
            }
            photosController.requestModelBuild()
        }
    }

    companion object {
        fun newInstance(title: String) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MvRx.KEY_ARG, MovieArgs(title))
            }
        }
    }
}