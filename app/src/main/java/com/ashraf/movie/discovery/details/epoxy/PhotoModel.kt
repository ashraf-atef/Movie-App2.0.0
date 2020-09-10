package com.ashraf.movie.discovery.details.epoxy

import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ashraf.movie.R
import com.bumptech.glide.Glide
import com.example.epoxyexample.common.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_photo)
abstract class PhotoModel : EpoxyModelWithHolder<PhotoModel.Holder>() {

    @EpoxyAttribute
    lateinit var photoUrl: String

    override fun bind(holder: Holder) {
        with(holder) {
            Glide.with(holder.ivMoviePhoto.context)
                .load(photoUrl)
                .placeholder(R.drawable.ic_placholder)
                .into(holder.ivMoviePhoto)
        }
    }

    class Holder : KotlinEpoxyHolder() {
        val ivMoviePhoto by bind<ImageView>(R.id.iv_movie_photo)
    }
}
