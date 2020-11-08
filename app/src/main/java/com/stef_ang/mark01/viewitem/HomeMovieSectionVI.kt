package com.stef_ang.mark01.viewitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.stef_ang.mark01.R
import com.stef_ang.mark01.databinding.ItemHomeMovieSectionBinding
import com.stef_ang.mark01.domain.HomeMovie

class HomeMovieSectionVI(
    val title: String,
    val movies: List<HomeMovie>
) : AbstractBindingItem<ItemHomeMovieSectionBinding>() {

    private val itemAdapter = ItemAdapter<HomeMovieSmallVI>()
    private val fastAdapter by lazy {
        FastAdapter.with(itemAdapter).apply {
            setHasStableIds(true)
        }
    }

    init {
        identifier = title.hashCode().toLong()
    }

    override val type: Int
        get() = R.id.item_home_movie_section

    override fun bindView(binding: ItemHomeMovieSectionBinding, payloads: List<Any>) {
        with(binding) {
            textTitle.text = title
            buttonAll.text = "See All"
            recyclerView.adapter = fastAdapter
        }
        itemAdapter.setNewList(movies.map { movie -> HomeMovieSmallVI(movie) })
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemHomeMovieSectionBinding {
        val binding = ItemHomeMovieSectionBinding.inflate(inflater, parent, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(parent?.context, RecyclerView.HORIZONTAL, false)
        return binding
    }
}