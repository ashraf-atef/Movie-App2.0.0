package com.ashraf.movie.discovery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashraf.movie.R
import com.ashraf.movie.discovery.movies.MoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, MoviesFragment.newInstance())
                .commitAllowingStateLoss()
    }
}
