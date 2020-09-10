package com.ashraf.movie.discovery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashraf.movie.R
import com.ashraf.movie.discovery.data.local.Movie
import com.ashraf.movie.discovery.details.MovieDetailsFragment
import com.ashraf.movie.discovery.movies.MoviesFragment

class MainActivity : AppCompatActivity() , MainActivityListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, MoviesFragment.newInstance())
                .commitAllowingStateLoss()
    }

    override fun openMovieDetails(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, MovieDetailsFragment.newInstance(movie.title))
            .addToBackStack("MOVIE_DETAILS")
            .commit()
    }
}
