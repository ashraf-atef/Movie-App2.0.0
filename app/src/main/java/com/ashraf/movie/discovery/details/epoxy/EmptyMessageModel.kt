package com.ashraf.movie.discovery.details.epoxy

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.ashraf.movie.R
import com.example.epoxyexample.common.KotlinEpoxyHolder

@EpoxyModelClass(layout = R.layout.item_empty)
abstract class EmptyMessageModel : EpoxyModelWithHolder<EmptyMessageModel.Holder>() {

    @EpoxyAttribute
    lateinit var message: String

    override fun bind(holder: Holder) {
        holder.tvEmptyMessage.text = message
    }

    class Holder : KotlinEpoxyHolder() {
        val tvEmptyMessage by bind<TextView>(R.id.tv_empty_message)
    }
}
