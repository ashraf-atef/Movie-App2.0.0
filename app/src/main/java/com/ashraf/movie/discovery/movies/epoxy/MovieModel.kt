package com.ashraf.movie.discovery.movies.epoxy

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ashraf.movie.R
import com.ashraf.movie.discovery.data.local.Movie
import com.example.epoxyexample.common.KotlinEpoxyHolder
import java.util.*

@EpoxyModelClass(layout = R.layout.item_movie)
abstract class MovieModel : EpoxyModelWithHolder<MovieModel.Holder>() {

    @EpoxyAttribute
    lateinit var movie: Movie

    override fun bind(holder: Holder) {
        with(holder) {
            val rnd = Random()
            val color: Int = Color.argb(
                255,
                rnd.nextInt(256),
                rnd.nextInt(256),
                rnd.nextInt(256)
            )
            ivMovieBanner.setBackgroundColor(color)
            tvMovieTitle.text = movie.title
            tvMovieRate.text = movie.rating.toString()
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val ivMovieBanner by bind<ImageView>(R.id.iv_movie_banner)
        val tvMovieTitle by bind<TextView>(R.id.tv_movie_title)
        val tvMovieRate by bind<TextView>(R.id.tv_movie_rate)
    }
}
