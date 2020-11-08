package com.stef_ang.mark01.viewitem

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.stef_ang.mark01.R
import com.stef_ang.mark01.databinding.ItemMovieSmallBinding
import com.stef_ang.mark01.domain.HomeMovie
import com.stef_ang.mark01.util.ImageUtils

class HomeMovieSmallVI(val data: HomeMovie) : AbstractBindingItem<ItemMovieSmallBinding>() {

    init {
        identifier = data.id.toLong()
    }

    override val type: Int
        get() = R.id.item_home_movie_small

    override fun bindView(binding: ItemMovieSmallBinding, payloads: List<Any>) {
        with(binding) {
            data.image?.let { ImageUtils.loadImage(it, image) }
            textTitle.text = data.title
        }
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemMovieSmallBinding {
        return ItemMovieSmallBinding.inflate(inflater, parent, false)
    }
}