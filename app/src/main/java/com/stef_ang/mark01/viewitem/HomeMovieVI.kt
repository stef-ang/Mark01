package com.stef_ang.mark01.viewitem

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.stef_ang.mark01.R
import com.stef_ang.mark01.databinding.ItemMovieBinding
import com.stef_ang.mark01.util.ImageUtils
import com.stef_ang.mark01.viewmodel.HomeMovieData

class HomeMovieVI(val data: HomeMovieData): AbstractBindingItem<ItemMovieBinding>() {

    override val type: Int
        get() = R.id.item_home_movie

    override fun bindView(binding: ItemMovieBinding, payloads: List<Any>) {
        with(binding) {
            textTitle.text = data.title
            textOverview.text = data.overview
            textDate.text = data.releaseDate
            textRating.apply {
                text = resources.getString(R.string.text_movie_rating, data.rating.toString())
            }
            data.image?.let { ImageUtils.loadImage(it, image) }
        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemMovieBinding {
        return ItemMovieBinding.inflate(inflater, parent, false)
    }
}